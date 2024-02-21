
import InstructorMainLayout from '@/layouts/instructor';
import { PATH_PAGES } from '@/routes/paths';
import { KeyboardArrowLeft, KeyboardArrowRight } from '@mui/icons-material';
import { Box, Button, FormControl, MenuItem, MobileStepper, Paper, Select, Stack, Step, StepLabel, Stepper, TextField, Typography } from '@mui/material';
import { useRouter } from 'next/router';
import { useEffect, useState } from 'react';
import { useTheme } from '@mui/material/styles';
import { RHFSelect } from '@/components/hook-form';
// ----------------------------------------------------------------------

// StepCreateCourse.getLayout = (page) => <InstructorMainLayout> {page} </InstructorMainLayout>;

// ----------------------------------------------------------------------
const steps = [
    {
        label: 'Input Title',
    },
    {
        label: 'Select Category',
    },
];

const category = [
    { key: 1, value: 'It' },
    { key: 2, value: 'It2' },
    { key: 3, value: 'It3' },
    { key: 4, value: 'It4' }
]

export default function StepCreateCourse() {

    const { push } = useRouter()

    const theme = useTheme();
    const [activeStep, setActiveStep] = useState(0);
    const maxSteps = steps.length;
    const [inputValue, setInputValue] = useState('');
    const [categoryID, setCategoryID] = useState(0)
    const [title, setTitle] = useState('');

    // Cập nhật giá trị title mỗi khi nó thay đổi
    useEffect(() => {
        const titleObject = { title: title, category_id: categoryID };
        localStorage.setItem('steps_course', JSON.stringify(titleObject));
    }, [title, categoryID]);

    const handleTitleChange = (event) => {
        setTitle(event.target.value);
    };

    const handleNext = () => {
        if (activeStep === maxSteps - 1) {
            push(`${PATH_PAGES.instructor.course.new}`)
        } else {
            setActiveStep((prevActiveStep) => prevActiveStep + 1);
        }
    };

    const handleBack = () => {
        console.log(activeStep);
        setActiveStep((prevActiveStep) => prevActiveStep - 1);
    };
    const handleInputChange = (event) => {
        setInputValue(event.target.value);
    };
    const handleSetCategory = (e) => {
        // setCategoryID(item)
        setCategoryID(e.target.value);
    }


    return (
        <Box sx={{ width: '80%', marginTop: '60px', marginLeft: '100px', flexGrow: 1 }}>
            {
                activeStep === 0 ? (
                    <Stack
                        direction="column"
                        justifyContent="center"
                        alignItems="center"
                        spacing={5}
                    >
                        <Typography sx={{ fontSize: '30px', fontWeight: '800', fontFamily: 'Times New Roman' }}>{steps[activeStep].label}</Typography>
                        <TextField
                            sx={{ width: '60%', mr: 2 }}
                            name='title'
                            id="outlined-required"
                            label="Title"
                            placeholder='title'
                            value={title}
                            onChange={handleTitleChange}
                        // name='title'
                        // id="outlined-required"
                        // label="Title"
                        // placeholder='title'
                        />
                    </Stack>
                ) : (
                    <Stack
                        direction="column"
                        justifyContent="center"
                        alignItems="center"
                        spacing={3}
                    >
                        <Typography sx={{ fontSize: '30px', fontWeight: '800', fontFamily: 'Times New Roman' }}>{steps[activeStep].label}</Typography>
                        <FormControl sx={{ width: '60%', mr: 2 }}>
                            <Select
                                labelId="categoryId"
                                id="categoryId"
                                value={categoryID}
                                onChange={(e) => { handleSetCategory(e) }}
                                sx={{ border: '1px solid #C3C3C3', '&:hover': { borderColor: '#158E4B' } }}
                            >
                                <MenuItem value={0}>All Category</MenuItem>
                                {
                                    category?.map((item, index) => (
                                        <MenuItem key={index} value={item.key}>{item.value}</MenuItem>
                                    ))
                                }
                            </Select>
                        </FormControl>
                        {/* <RHFSelect
                            id='categoryId'
                            name='categoryId'
                            label='Category'
                            sx={{ mr: 2 }}
                            onChange={handleSetCategory}
                            value={categoryID}
                        >

                            <>
                                <option value={0}>Select Part</option>
                                {
                                    category?.map((item, index) => (
                                        <option key={index} value={item.key}>{item.value}</option>
                                    ))
                                }
                            </>


                        </RHFSelect> */}
                    </Stack>
                )
            }
            <MobileStepper
                sx={{ marginBottom: '30px' }}
                variant="progress"
                steps={maxSteps}
                position="bottom"
                activeStep={activeStep}
                nextButton={
                    <Button
                        size="small"
                        onClick={handleNext}
                        disabled={activeStep === maxSteps}
                    >
                        {activeStep === steps.length - 1 ? 'Finish' : 'Next'}
                        {theme.direction === 'rtl' ? (
                            <KeyboardArrowLeft />
                        ) : (
                            <KeyboardArrowRight />
                        )}
                    </Button>
                }
                backButton={
                    <Button size="small" onClick={handleBack} disabled={activeStep === 0}>
                        {theme.direction === 'rtl' ? (
                            <KeyboardArrowRight />
                        ) : (
                            <KeyboardArrowLeft />
                        )}
                        Back
                    </Button>
                }
            />
        </Box>
    );
}
