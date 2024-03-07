package com.llp.userservice.services.impl;

import com.llp.sharedproject.exceptions.BadRequestException;
import com.llp.sharedproject.exceptions.ConflictException;
import com.llp.sharedproject.exceptions.InternalServerException;
import com.llp.sharedproject.exceptions.NotFoundException;
import com.llp.userservice.clients.CourseClient;
import com.llp.userservice.clients.dtos.CourseTeacherResponse;
import com.llp.userservice.dtos.user.*;
import com.llp.userservice.entities.Role;
import com.llp.userservice.entities.User;
import com.llp.userservice.entities.UserRole;
import com.llp.userservice.entities.keys.UserRoleKey;
import com.llp.userservice.mappers.UserMapper;
import com.llp.userservice.repositories.RoleRepository;
import com.llp.userservice.repositories.UserRepository;
import com.llp.userservice.repositories.UserRoleRepository;
import com.llp.userservice.repositories.YourCourseRepository;
import com.llp.userservice.services.JwtService;
import com.llp.userservice.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private static final String root = System.getProperty("user.dir") + "/shared-project/src/main/resources/static/";
    private static final String directory = "images/user/";

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final UserRoleRepository userRoleRepository;
    private final YourCourseRepository yourCourseRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final EmailService emailService;
    private final CourseClient courseClient;
    @Override
    public AuthenticationResponse register(RegisterRequest request) {
        try {
            if(!userRepository.findByEmail(request.getEmail()).isEmpty()){
                throw new ConflictException("Already has this user");
            }
            Role role = roleRepository.findByName("USER");

            if (role == null) {
                throw new IllegalStateException("Default user role not found");
            }
            var user = User.builder()
                    .fullname(request.getFullName())
                    .email(request.getEmail())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .verified(true)
                    .imageLink("default-avatar.png")
                    .participateDay(LocalDate.now())
                    .build();
            userRepository.save(user);
            userRoleRepository.create(user.getId().intValue(), role.getId().intValue());
            List<UserRole> userRoleList = new ArrayList<>();
            userRoleList.add(userRoleRepository.getById(new UserRoleKey((long) user.getId().intValue(), (long) role.getId().intValue())));
            user.setUserRoles(userRoleList);
            var token = jwtService.generateToken(user);
            return AuthenticationResponse.builder()
                    .token(token)
                    .build();
        } catch (ConflictException e){
            throw new ConflictException(e.getMessage());
        } catch (Exception e){
            throw new InternalServerException("Server Error");
        }
    }

    @Override
    public AuthenticationResponse loginViaForm(AuthenticationRequest request) {
        try {
            var user = userRepository.findByEmail(request.getEmail())
                    .orElseThrow(() -> new NotFoundException("There is no account like that. Please register a new one"));
            if(!user.isVerified()){
                throw new BadRequestException("This account is login by google");
            }
            if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
                throw new BadCredentialsException("Invalid password");
            }
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getEmail(),
                            request.getPassword()
                    )
            );
            var token = jwtService.generateToken(user);
            return AuthenticationResponse.builder()
                    .token(token)
                    .build();
        } catch (NotFoundException e){
            throw new NotFoundException(e.getMessage());
        } catch (BadCredentialsException e){
            throw new BadCredentialsException(e.getMessage());
        } catch (BadRequestException e){
            throw new BadRequestException(e.getMessage());
        } catch (Exception e){
            throw new InternalServerException("Server Error");
        }
    }

    @Override
    public AuthenticationResponse registerAsAdmin(RegisterRequest request) {
        try {
            if(!userRepository.findByEmail(request.getEmail()).isEmpty()){
                throw new ConflictException("Already has this admin");
            }
            Role role = roleRepository.findByName("ADMIN");

            if (role == null) {
                throw new IllegalStateException("Default admin role not found");
            }
            var user = User.builder()
                    .fullname(request.getFullName())
                    .email(request.getEmail())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .verified(true)
                    .imageLink("default-avatar.png")
                    .participateDay(LocalDate.now())
                    .build();
            userRepository.save(user);
            userRoleRepository.create(user.getId().intValue(), role.getId().intValue());
            userRoleRepository.create(user.getId().intValue(), role.getId().intValue());
            List<UserRole> userRoleList = new ArrayList<>();
            userRoleList.add(userRoleRepository.getById(new UserRoleKey((long) user.getId().intValue(), (long) role.getId().intValue())));
            user.setUserRoles(userRoleList);
            var token = jwtService.generateToken(user);
            return AuthenticationResponse.builder()
                    .token(token)
                    .build();
        } catch (ConflictException e){
            throw new ConflictException(e.getMessage());
        } catch (Exception e){
            throw new InternalServerException("Server Error");
        }
    }

    @Override
    public UserResponse getUserInformation(String username) {
        try {
            var user = userRepository.findByEmail(username)
                    .orElseThrow(() -> new NotFoundException("User not found"));
            return UserMapper.convertToUserResponse(user);
        } catch (NotFoundException e){
            throw new NotFoundException(e.getMessage());
        } catch (Exception e){
            throw new InternalServerException("Server Error");
        }
    }

    @Override
    public void updateInformation(String username, UserUpdateRequest request) {
        try {
            if(Objects.isNull(request)){
                throw new BadRequestException("Input must has at least one value");
            }
            var user = userRepository.findByEmail(username)
                    .orElseThrow(() -> new NotFoundException("User not found"));
            user.setFullname(request.getFullname() != null ? request.getFullname() : user.getFullname());
            user.setHeadline(request.getHeadline() != null ? request.getHeadline() : user.getHeadline());
            user.setBiography(request.getBiography() != null ? request.getBiography() : user.getBiography());
            user.setTwitterLink(request.getTwitterLink() != null ? request.getTwitterLink() : user.getTwitterLink());
            user.setFacebookLink(request.getFacebookLink() != null ? request.getFacebookLink() : user.getFacebookLink());
            user.setLinkedinLink(request.getLinkedinLink() != null ? request.getLinkedinLink() : user.getLinkedinLink());
            user.setYoutubeLink(request.getYoutubeLink() != null ? request.getYoutubeLink() : user.getYoutubeLink());
            userRepository.save(user);
        } catch (BadRequestException e){
            throw new BadRequestException(e.getMessage());
        } catch (NotFoundException e){
            throw new NotFoundException(e.getMessage());
        } catch (Exception e){
            throw new InternalServerException("Server Error");
        }
    }

    @Override
    public void updateAvatar(String username, MultipartFile avatar) {
        try {
            var user = userRepository.findByEmail(username)
                    .orElseThrow(() -> new NotFoundException("User not found"));
            String pastPath = user.getImageLink();
            insertImage(user, avatar);
            deleteImage(pastPath);
        } catch (NotFoundException e){
            throw new NotFoundException(e.getMessage());
        } catch (Exception e){
            throw new InternalServerException("Server Error");
        }
    }

    @Override
    public InstructorResponse getInstructorInformation(int id) {
        try {
            var user = userRepository.getById(id)
                    .orElseThrow(() -> new NotFoundException("User not found"));
            InstructorResponse instructor = UserMapper.convertToInstructorResponse(user);
            List<CourseTeacherResponse> courses = courseClient.getByTeacher(user.getId().intValue());
            double ratingTotal = 0;
            int ratingNumTotal = 0;
            int courseTotal = courses.size();
            int studentNum = 0;
            for (var course:courses) {
                studentNum += yourCourseRepository.countByCourseId(course.getId().toString());
                if(course.getRating() == 0 && course.getRatingNum() == 0){
                    courseTotal--;
                } else {
                    ratingTotal += course.getRating();
                    ratingNumTotal += course.getRatingNum();
                }
            }
            instructor.setStudentNum(studentNum);
            instructor.setCourseNum((int) courses.stream().count());
            instructor.setRating(Math.round(ratingTotal/courseTotal * 10.0) / 10.0);
            instructor.setReviewNum(ratingNumTotal);
            return instructor;
        } catch (NotFoundException e){
            throw new NotFoundException(e.getMessage());
        } catch (Exception e){
            throw new InternalServerException("Server Error");
        }
    }

    @Override
    public UserResponse getOtherUserInformation(int id) {
        try {
            var user = userRepository.getById(id)
                    .orElseThrow(() -> new NotFoundException("User not found"));
            return UserMapper.convertToUserResponse(user);
        } catch (NotFoundException e){
            throw new NotFoundException(e.getMessage());
        } catch (Exception e){
            throw new InternalServerException("Server Error");
        }
    }

    @Override
    public List<InstructorResponse> getPopularInstructorByCategory(int categoryId) {
        try {
            List<Integer> userIds = courseClient.getAllTeacherByCategory(categoryId);
            List<InstructorResponse> data = new ArrayList<>();
            for (var userId:userIds) {
                data.add(getInstructorInformation(userId));
            }
            // Sort the list based on the rating in descending order
            Collections.sort(data, Comparator.comparing(InstructorResponse::getRating, Comparator.reverseOrder()));

            // Get the top 6 users with the highest ratings
            List<InstructorResponse> top6Instructor = data.subList(0, Math.min(6, data.size()));
            return data;
        } catch (Exception e){
            throw new InternalServerException("Server Error");
        }
    }

    @Override
    public List<InstructorResponse> getPopularInstructorBySubCategory(int subCategoryId) {
        try {
            List<Integer> userIds = courseClient.getAllTeacherBySubCategory(subCategoryId);
            List<InstructorResponse> data = new ArrayList<>();
            for (var userId:userIds) {
                data.add(getInstructorInformation(userId));
            }
            // Sort the list based on the rating in descending order
            Collections.sort(data, Comparator.comparing(InstructorResponse::getRating, Comparator.reverseOrder()));

            // Get the top 6 instructors with the highest ratings
            List<InstructorResponse> top6Instructor = data.subList(0, Math.min(6, data.size()));
            return data;
        } catch (Exception e){
            throw new InternalServerException("Server Error");
        }
    }

    @Override
    public void forgetPassword(String email) {
        try {
            User user = userRepository.findByEmail(email)
                    .orElseThrow(() -> new NotFoundException("There is no account like that. Please register a new one"));
            String otp = generateOtp();
            user.setOtp(Integer.parseInt(otp));
            user.setPassword(passwordEncoder.encode(otp));
            userRepository.save(user);
            sendVerificationEmail(email,otp);
        } catch (NotFoundException e){
            throw new NotFoundException(e.getMessage());
        } catch (Exception e){
            throw new InternalServerException("Server Error");
        }
    }

    @Override
    public void changePassword(int userId, String oldPass, String newPass, String reNewPass) {
        try {
            if (!newPass.equals(reNewPass)){
                throw new BadRequestException("New password and Re-enter new password must match");
            }
            var user = userRepository.findById((long) userId)
                    .orElseThrow(() -> new NotFoundException("Not found any account"));
            if (!passwordEncoder.matches(oldPass, user.getPassword())) {
                throw new BadCredentialsException("Invalid old password");
            }
            user.setPassword(passwordEncoder.encode(newPass));
            userRepository.save(user);
        } catch (NotFoundException e){
            throw new NotFoundException(e.getMessage());
        } catch (BadRequestException e){
            throw new BadRequestException(e.getMessage());
        } catch (BadCredentialsException e){
            throw new BadCredentialsException(e.getMessage());
        } catch (Exception e){
            throw new InternalServerException("Server Error");
        }
    }

    @Override
    public AuthenticationResponse loginViaGoogle(RegisterGoogleRequest request) {
        try {
            if(!userRepository.findByEmail(request.getEmail()).isEmpty()){
                User user = userRepository.findByEmail(request.getEmail()).get();
                if(user.isVerified()){
                    throw new BadRequestException("This account is login by form");
                }
                authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(
                                request.getEmail(),
                                null
                        )
                );
                var token = jwtService.generateToken(user);
                return AuthenticationResponse.builder()
                        .token(token)
                        .build();
            } else {
                Role role = roleRepository.findByName("USER");
                if (role == null) {
                    throw new IllegalStateException("Default user role not found");
                }
                var user = User.builder()
                        .fullname(request.getFullName())
                        .email(request.getEmail())
                        .password(null)
                        .verified(false)
                        .imageLink("default-avatar.png")
                        .participateDay(LocalDate.now())
                        .build();
                userRepository.save(user);
                userRoleRepository.create(user.getId().intValue(), role.getId().intValue());
                List<UserRole> userRoleList = new ArrayList<>();
                userRoleList.add(userRoleRepository.getById(new UserRoleKey((long) user.getId().intValue(), (long) role.getId().intValue())));
                user.setUserRoles(userRoleList);
                var token = jwtService.generateToken(user);
                return AuthenticationResponse.builder()
                        .token(token)
                        .build();
            }
        }  catch (Exception e){
            throw new InternalServerException("Server Error");
        }
    }


    private void insertImage(User user, MultipartFile imageLink) {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS");
        String formattedDateTime = now.format(formatter);
        String randomUUID = UUID.randomUUID().toString().replace("-", "");
        String originalFilename = imageLink.getOriginalFilename();
        String fileExtension = originalFilename.substring(originalFilename.lastIndexOf("."));
        String newFilename = formattedDateTime + randomUUID + fileExtension;
        Path filePath = Paths.get(root, directory, newFilename);
        try {
            imageLink.transferTo(new File(String.valueOf(filePath)));
            user.setImageLink(newFilename);
            userRepository.save(user);
        } catch (IOException e) {
            throw new NotFoundException("Not found file");
        }
    }

    private void deleteImage(String pastPath) {
        Path filePath = Paths.get(root, pastPath);
        File file = new File(String.valueOf(filePath));
        file.delete();
    }

    private String generateOtp(){
        Random random = new Random();
        int otpValue = 100000 + random.nextInt(900000);
        return String.valueOf(otpValue);
    }

    private void sendVerificationEmail(String email, String otp){
        String subject = "Online Web LLP";
        String body = "Your verification otp is: " +otp + ". Your old password will also be set to this otp";
        emailService.sendEmail(email,subject,body);
    }
}
