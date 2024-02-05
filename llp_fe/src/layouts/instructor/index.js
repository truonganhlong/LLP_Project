import PropTypes from 'prop-types';
// next
import dynamic from 'next/dynamic';
import { useRouter } from 'next/router';
// @mui
import { AppBar, Avatar, Box, Button, Divider, Drawer, IconButton, List, ListItem, ListItemButton, ListItemIcon, ListItemText, Stack, Toolbar, Tooltip, Typography } from '@mui/material';
//
import MenuIcon from '@mui/icons-material/Menu';
import { useState } from 'react';
import { EmailInboxIcon } from '@/assets/icons';
import MailIcon from '@mui/icons-material/Mail';
import ChevronLeftIcon from '@mui/icons-material/ChevronLeft';
import ChevronRightIcon from '@mui/icons-material/ChevronRight';
import { styled, useTheme } from '@mui/material/styles';
import Link from 'next/link';
import { AccountCircle } from '@mui/icons-material';
import { PATH_PAGES } from '@/routes/paths';
// import styles from './layout-instructor.css'
//----------------------------------------------------------------------
const Header = dynamic(() => import('./Header'), { ssr: false });
const Footer = dynamic(() => import('./Footer'), { ssr: false });

// ----------------------------------------------------------------------

InstructorMainLayout.propTypes = {
  children: PropTypes.node,
};

const drawerWidth = 240;

const openedMixin = (theme) => ({
  width: drawerWidth,
  transition: theme.transitions.create('width', {
    easing: theme.transitions.easing.sharp,
    duration: theme.transitions.duration.enteringScreen,
  }),
  overflowX: 'hidden',
});

const closedMixin = (theme) => ({
  transition: theme.transitions.create('width', {
    easing: theme.transitions.easing.sharp,
    duration: theme.transitions.duration.leavingScreen,
  }),
  overflowX: 'hidden',
  width: `calc(${theme.spacing(7)} + 1px)`,
  [theme.breakpoints.up('sm')]: {
    width: `calc(${theme.spacing(8)} + 1px)`,
  },
});

const DrawerHeader = styled('div')(({ theme }) => ({
  display: 'flex',
  alignItems: 'center',
  justifyContent: 'flex-end',
  padding: theme.spacing(0, 1),
  // necessary for content to be below app bar
  ...theme.mixins.toolbar,
}));

const AppBar1 = styled(AppBar, {
  shouldForwardProp: (prop) => prop !== 'open',
})(({ theme, open }) => ({
  zIndex: theme.zIndex.drawer + 1,
  transition: theme.transitions.create(['width', 'margin'], {
    easing: theme.transitions.easing.sharp,
    duration: theme.transitions.duration.leavingScreen,
  }),
  ...(open && {
    marginLeft: drawerWidth,
    width: `calc(100% - ${drawerWidth}px)`,
    transition: theme.transitions.create(['width', 'margin'], {
      easing: theme.transitions.easing.sharp,
      duration: theme.transitions.duration.enteringScreen,
    }),
  }),
}));

const Drawer1 = styled(Drawer, { shouldForwardProp: (prop) => prop !== 'open' })(
  ({ theme, open }) => ({
    width: drawerWidth,
    flexShrink: 0,
    whiteSpace: 'nowrap',
    boxSizing: 'border-box',
    ...(open && {
      ...openedMixin(theme),
      '& .MuiDrawer-paper': openedMixin(theme),
    }),
    ...(!open && {
      ...closedMixin(theme),
      '& .MuiDrawer-paper': closedMixin(theme),
    }),
  }),
);


const CustomCourseIcon = () => {
  return (
    <svg width="30" height="30" viewBox="0 0 70 66" fill="none" xmlns="http://www.w3.org/2000/svg">
      <path d="M4 52V9C4 6.23858 6.23858 4 9 4H61C63.7614 4 66 6.23858 66 9V52C66 54.7614 63.7614 57 61 57H9C6.23858 57 4 54.7614 4 52Z" stroke="black" strokeWidth="7" />
      <path d="M49.9999 31.0001L27.5007 43.991L27.5001 18.0102L49.9999 31.0001Z" fill="black" />
      <path d="M20 63L50 63" stroke="black" strokeWidth="5" />
    </svg>

  );
}

const CustomComunIcon = () => {
  return (
    <svg xmlns="http://www.w3.org/2000/svg" xmlnsXlink="http://www.w3.org/1999/xlink" fill="#000000" width="30px" height="30px" viewBox="0 0 64 64" version="1.1">
      <g xmlns="http://www.w3.org/2000/svg" id="communication">
        <g>
          <path d="M35,30c2.2,0,4-1.8,4-4s-1.8-4-4-4c-2.2,0-4,1.8-4,4S32.8,30,35,30z M35,24c1.1,0,2,0.9,2,2s-0.9,2-2,2s-2-0.9-2-2    S33.9,24,35,24z" />

          <path d="M24,30c2.2,0,4-1.8,4-4s-1.8-4-4-4s-4,1.8-4,4S21.8,30,24,30z M24,24c1.1,0,2,0.9,2,2s-0.9,2-2,2s-2-0.9-2-2    S22.9,24,24,24z" />

          <path d="M13,22c-2.2,0-4,1.8-4,4s1.8,4,4,4s4-1.8,4-4S15.2,22,13,22z M13,28c-1.1,0-2-0.9-2-2s0.9-2,2-2s2,0.9,2,2S14.1,28,13,28z    " />

          <path d="M60.3,9.1c-0.4-0.1-0.8,0-1.1,0.3l-8.5,10.2C50,20.5,48.8,21,47.7,21H45v-6c0-3.3-2.7-6-6-6H9c-3.3,0-6,2.7-6,6v39    c0,0.4,0.3,0.8,0.7,0.9C3.8,55,3.9,55,4,55c0.3,0,0.6-0.1,0.8-0.4l8.5-10.2c0.8-0.9,1.9-1.4,3.1-1.4H19v6c0,3.3,2.7,6,6,6h30    c3.3,0,6-2.7,6-6V10C61,9.6,60.7,9.2,60.3,9.1z M11.7,43.2L5,51.2V15c0-2.2,1.8-4,4-4h30c2.2,0,4,1.8,4,4v22c0,2.2-1.8,4-4,4H16.3    C14.6,41,12.9,41.8,11.7,43.2z M59,49c0,2.2-1.8,4-4,4H25c-2.2,0-4-1.8-4-4v-6h18c3.3,0,6-2.7,6-6V23h2.7c1.8,0,3.5-0.8,4.6-2.2    l6.7-8.1V49z" />

          <path d="M47,38c0,2.2,1.8,4,4,4s4-1.8,4-4s-1.8-4-4-4S47,35.8,47,38z M53,38c0,1.1-0.9,2-2,2s-2-0.9-2-2s0.9-2,2-2S53,36.9,53,38z    " />
        </g>
      </g>
    </svg>
  );
}



const CustomPerforIcon = () => {
  return (
    <svg xmlns="http://www.w3.org/2000/svg" xmlnsXlink="http://www.w3.org/1999/xlink" version="1.1" id="_x32_" width="30px" height="30px" viewBox="0 0 512 512" >
      <g>
        <rect x="56" y="317.484" className="st0" width="80" height="146.516" />
        <rect x="176" y="237.484" className="st0" width="80" height="226.516" />
        <rect x="296" y="141.484" className="st0" width="80" height="322.516" />
        <rect x="416" y="45.484" className="st0" width="80" height="418.516" />
        <polygon className="st0" points="16,496 16,0 0,0 0,496 0,512 16,512 512,512 512,496  " />
      </g>
    </svg>
  );
}


export default function InstructorMainLayout({ children }) {
  const item = [
    {
      value: 'course',
      name: 'Course',
      icon: <CustomCourseIcon />
    },
    {
      value: 'communication',
      name: 'Communication',
      icon: <CustomComunIcon />
    },
    {
      value: 'performance',
      name: 'Performance',
      icon: <CustomPerforIcon />
    }
  ]
  const theme = useTheme();
  const { pathname, push } = useRouter();
  const [open, setOpen] = useState(false);
  const [value, setValue] = useState(item[0].value)
  const handleDrawerOpen = () => {
    setOpen(true);
  };

  const handleDrawerClose = () => {
    setOpen(false);
  };
  const handleChangeValue = (item) => {
    setValue(item)
    if (item === 'course') {
      push(`${PATH_PAGES.instructor.course.root}`)
    } else if (item === 'performance') {
      push(`${PATH_PAGES.instructor.performance.root}`)
    } else if (item === 'communication') {
      push(`${PATH_PAGES.instructor.communication.root}`)
    }
  }


  const isHome = pathname === '/page/instructor';


  return (
    <Box>
      <Header />
      <Box
        component="main"
      // sx={{
      //   flexGrow: 1,
      //   ...(!isHome && {
      //     pt: { xs: 8, md: 11 },
      //   }),
      // }}
      >


        <AppBar1 position="fixed" open={open} sx={{ backgroundColor: 'white' }}>
          <Toolbar>
            <IconButton
              aria-label="open drawer"
              onClick={handleDrawerOpen}
              edge="start"
              sx={{
                color: 'black',
                marginRight: 5,
                ...(open && { display: 'none' }),
              }}
            >
              <MenuIcon />
            </IconButton>
            <Stack
              direction="row"
              justifyContent="end"
              alignItems="center"
              spacing={3}
              sx={{ marginLeft: '80%' }}
            >
              <Button href="#" sx={{ color: 'black', fontSize: '13px' }} >
                Student
              </Button>
              <Tooltip title="Open settings">
                <IconButton sx={{ p: 0 }} color='black'>
                  <Avatar alt="Avatar" src="/image/homepage/avatar-icon.png" />
                </IconButton>
              </Tooltip>
            </Stack>
          </Toolbar>
        </AppBar1>
        <Drawer1 variant="permanent" open={open}>
          <DrawerHeader>
            <IconButton onClick={handleDrawerClose}>
              {theme.direction === 'rtl' ? <ChevronRightIcon /> : <ChevronLeftIcon />}
            </IconButton>
          </DrawerHeader>
          <Divider />
          <List spacing={3}>
            {item?.map((text, index) => (
              <Stack justifyContent="flex-start" key={index} sx={{ display: 'block', margin: '20px 0 20px 0' }}>
                {/* <div style={{ width: '3px', height: '50px', background: '#F9B03C' }}></div> */}
                <Button
                  sx={{
                    minHeight: 48,
                    justifyContent: open ? 'initial' : 'center',
                    px: 2.5,
                  }}
                  onClick={() => handleChangeValue(text.value)}
                  startIcon={text?.icon}
                >
                  <Typography sx={{
                    opacity: open ? 1 : 0,
                    color: 'black',
                    paddingLeft: '20px',
                    fontWeight: '600',
                    fontFamily: 'Montserrat'
                  }}>
                    {text?.name}
                  </Typography>
                </Button>
              </Stack>
            ))}
          </List>

        </Drawer1>
        {children}
      </Box>

      {/* <Footer /> */}
    </Box>
  );
}
