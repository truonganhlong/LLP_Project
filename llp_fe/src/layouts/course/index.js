import { styled, alpha } from '@mui/material/styles';
import AppBar from '@mui/material/AppBar';
import Box from '@mui/material/Box';
import Toolbar from '@mui/material/Toolbar';
import IconButton from '@mui/material/IconButton';
import Typography from '@mui/material/Typography';
import React, { useState } from 'react';
import Iconify from '@/components/iconify';
import FormProvider from '@/components/hook-form/FormProvider';
import { yupResolver } from '@hookform/resolvers/yup';
import { useForm } from 'react-hook-form';
import * as Yup from 'yup';
import { Button, Container, TextField, useScrollTrigger } from '@mui/material';
import { CustomCartIcon } from '@/utils/icon';
import PropTypes from 'prop-types';
import { useRouter } from 'next/router';
import { PATH_PAGES } from '@/routes/paths';
import FooterCourse from './footer';
//-----------------------------------------------------------------


function ElevationScroll(props) {
  const { children, window } = props;
  // Note that you normally won't need to set the window ref as useScrollTrigger
  // will default to window.
  // This is only being set here because the demo is in an iframe.
  const trigger = useScrollTrigger({
    disableHysteresis: true,
    threshold: 0,
    target: window ? window() : undefined,
  });

  return React.cloneElement(children, {
    elevation: trigger ? 4 : 0,
  });
}

ElevationScroll.propTypes = {
  children: PropTypes.element.isRequired,
  /**
   * Injected by the documentation to work in an iframe.
   * You won't need it on your project.
   */
  window: PropTypes.func,
};
const items = [

  {
    key: '2',
    label: (<a style={{ marginTop: '20px' }}>Category</a>),
    children: [
      {
        key: '2-1',
        label: 'Subcategory1',
        children: [
          {
            key: '3-1',
            label: 'topics',
          },
          {
            key: '3-2',
            label: 'topics 1',
          },
        ],
      },
      {
        key: '2-2',
        label: 'Sub category2',
        children: [
          {
            key: '3-3',
            label: 'topics 2',
          },
          {
            key: '3-4',
            label: 'topics 3',
          },
        ],
      },
    ],
  },
  {
    key: '3',
    label: (<a style={{ marginTop: '20px' }}>Category2</a>),
    children: [
      {
        key: '2-3',
        label: 'Subcategory 3',
        children: [
          {
            key: '3-5',
            label: 'Topics 4',
          },
          {
            key: '3-6',
            label: 'Topics 5',
          },
        ],
      },
      {
        key: '2-4',
        label: 'Subcategory4',
        children: [
          {
            key: '3-7',
            label: 'Topics 6',
          },
          {
            key: '3-8',
            label: 'Topics 7',
          },
        ],
      },
    ],
  },


];
export default function MainLayout({ children }) {
  const [anchorEl, setAnchorEl] = useState(null);
  const [mobileMoreAnchorEl, setMobileMoreAnchorEl] = useState(null);

  const isMenuOpen = Boolean(anchorEl);
  const isMobileMenuOpen = Boolean(mobileMoreAnchorEl);
  const { push, query: { cindex, course_id } } = useRouter();

  const [isOpen, setIsOpen] = useState(false);
  const [subMenuOpen1, setSubMenuOpen1] = useState(false);
  const [subMenuOpen2, setSubMenuOpen2] = useState(false);
  const [subMenuOpenSelected1, setSubMenuSelected1] = useState(null)
  const [subMenuOpenSelected2, setSubMenuSelected2] = useState(null)
  const handleProfileMenuOpen = (event) => {
    setAnchorEl(event.currentTarget);
  };

  const handleMobileMenuClose = () => {
    setMobileMoreAnchorEl(null);
  };

  const handleMenuClose = () => {
    setAnchorEl(null);
    handleMobileMenuClose();
  };

  const handleMobileMenuOpen = (event) => {
    setMobileMoreAnchorEl(event.currentTarget);
  };

  const menuId = 'primary-search-account-menu';


  const mobileMenuId = 'primary-search-account-menu-mobile';


  const validationSchema = Yup.object().shape({
    question: Yup.string().trim().required('Field not null'),
  })


  const methods = useForm({
    resolver: yupResolver(validationSchema),

  })

  const {
    reset,
    watch,
    control,
    setValue,
    handleSubmit,
    formState: { isSubmitting },
  } = methods;

  const onSubmit = async (data) => {
    console.log(data);
  }

  return (
    <Box sx={{ flexGrow: 1 }}>
      <style suppressHydrationWarning>{`
      .MuiButtonBase-root.MuiButton-root{
        text-transform:none!important;
        color:#000000!important;
        border-color:#000000!important
      }
      `}</style>
      <Container maxWidth={'100%'} >
        {/* <ElevationScroll> */}
        <AppBar color="transparent" sx={{ boxShadow: 0.3, paddingLeft: 8 }}>
          <FormProvider methods={methods} onSubmit={handleSubmit(onSubmit)}>
            <Toolbar>
              <Box display='flex' >
                <Typography
                  variant="h6"
                  noWrap
                  component="div"
                  sx={{ color: 'black', ml: 2, mr: 2 }}
                >
                  logo
                </Typography>
                <div style={{ position: 'relative', display: 'inline-block' }} onMouseLeave={() => setIsOpen(false)}>
                  <Button variant='text' onMouseOver={() => setIsOpen(true)}>Categories</Button>

                  {isOpen && (
                    <Box style={{ position: 'absolute', top: '100%', left: 0, backgroundColor: 'white', border: '1px solid #ccc' }}
                      onMouseLeave={() => { setSubMenuOpen1(false); setSubMenuSelected1(null); setSubMenuSelected2(null); }}
                    >
                      {items.map((item, index) => (
                        <div key={index} onMouseOver={() => { setSubMenuOpen1(true); setSubMenuSelected1(item.key) }}>
                          <Button sx={{ minWidth: '130px' }} variant='text'>{item.label}</Button>
                          {subMenuOpen1 && item.children && item.key === subMenuOpenSelected1 && (
                            <div style={{ width: '200px', border: 'none', position: 'absolute', top: 0, left: '100%', backgroundColor: 'white', border: '1px solid #ccc' }} onMouseLeave={() => setSubMenuOpen2(false)}>
                              {item.children.map((subItem, subIndex) => (
                                <div key={subIndex} onMouseOver={() => { setSubMenuOpen2(true); setSubMenuSelected2(subItem.key) }}>
                                  <Button sx={{ minWidth: '130px' }} variant='text'>{subItem.label}</Button>
                                  {subMenuOpen2 && subItem.children && subItem.key === subMenuOpenSelected2 && (
                                    <div style={{ position: 'absolute', border: 'none', top: 0, left: '100%', backgroundColor: 'white', border: '1px solid #ccc' }}>
                                      {subItem.children.map((subSubItem, subSubIndex) => (
                                        <Button sx={{ minWidth: '130px' }} variant='text' key={subSubIndex}>{subSubItem.label}</Button>
                                      ))}
                                    </div>
                                  )}
                                </div>
                              ))}
                            </div>
                          )}
                        </div>
                      ))}
                    </Box>
                  )}
                </div>

                <TextField
                  name='search'
                  placeholder={"Search"}
                  InputProps={{
                    startAdornment: (
                      <Iconify icon="eva:search-outline" sx={{ width: 25, height: 25, color: '#000000', paddingRight: '10px' }} />
                    )
                  }}
                  sx={{
                    border: '1px solid #C3C3C3', borderRadius: '10px', width: 500, height: 40, padding: '0 !important', '& .css-1d3z3hw-MuiOutlinedInput-notchedOutline': {
                      border: 'none',
                    },
                    '& .css-1o9s3wi-MuiInputBase-input-MuiOutlinedInput-input': {
                      padding: 1
                    },
                    '& input::placeholder': {
                      color: '#857E7E', /* Thay đổi màu chữ của placeholder thành màu đỏ ví dụ */
                    },
                    ml: 2,
                    mr: 2
                  }}
                // onFocus={() => setIsForcus(true)}
                // onBlur={() => setIsForcus(false)}
                // onChange={(e) => setKeyword(e.target.value)}
                // onKeyDown={handleSearch}
                />
              </Box>
              <Box>
                <Button onClick={() => push(`${PATH_PAGES.instructor.course.root}`)} variant="text" sx={{ mr: 2, ml: 2 }}>
                  Teach on LLP
                </Button>
                <IconButton>
                  <CustomCartIcon width={25} height={24} />
                </IconButton>
              </Box>

              <Box>
                <Button variant="outlined" sx={{ mr: 2, ml: 2 }}>
                  SignIn
                </Button>
                <Button variant="outlined" sx={{ mr: 2, ml: 2 }}>
                  SignUp
                </Button>

              </Box>

            </Toolbar>
          </FormProvider>
        </AppBar>
        {/* </ElevationScroll> */}



      </Container>
      {/* <Container sx={{mt:8}}> */}
      {children}
      {/* </Container> */}
      {/* {renderMobileMenu}
      {renderMenu} */}
      <FooterCourse />
    </Box>
  );
}