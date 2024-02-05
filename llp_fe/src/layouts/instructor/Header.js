import PropTypes from 'prop-types';
// @mui
import { useTheme } from '@mui/material/styles';
import { Box, Button, AppBar, Toolbar, Container, Link, Typography } from '@mui/material';
// hooks
import useOffSetTop from '../../hooks/useOffSetTop';
import useResponsive from '../../hooks/useResponsive';
// utils
import { bgBlur } from '../../utils/cssStyles';
// config
import { HEADER } from '../../config';
// routes
// components
// import Logo from '../../components/logo';
import Label from '../../components/label';
//
// import NavMobile from './nav/mobile';
// import navConfig from './nav/config';
// import NavDesktop from './nav/desktop';
import Logo from '@/components/logo';

// ----------------------------------------------------------------------

export default function Header() {
  const theme = useTheme();

  const isDesktop = useResponsive('up', 'md');

  const isOffset = useOffSetTop(HEADER.H_MAIN_DESKTOP);

  return (
    <AppBar color="transparent" sx={{ boxShadow: 0 }}>
      <Toolbar
        disableGutters
        // sx={{
        //   height: {
        //     xs: HEADER.H_MOBILE,
        //     md: HEADER.H_MAIN_DESKTOP,
        //   },
        //   transition: theme.transitions.create(['height', 'background-color'], {
        //     easing: theme.transitions.easing.easeInOut,
        //     duration: theme.transitions.duration.shorter,
        //   }),
        //   ...(isOffset && {
        //     ...bgBlur({ color: theme.palette.background.default }),
        //     height: {
        //       md: HEADER.H_MAIN_DESKTOP - 16,
        //     },
        //   }),
        // }}
      >
        <Container sx={{ display: 'flex', alignItems: 'center', justifyContent: 'space-between', ml: 1, mt: 4 }}>
          {/* <Logo /> */}

          <Typography
            variant="h7"
            color="green"
            noWrap
            style={{ color: 'green' }}
            sx={{ flexGrow: 2, mt: 3 }}
            alignSelf="center"
          >
            LLP
          </Typography>
        </Container>
      </Toolbar>

      {isOffset && <Shadow />}
    </AppBar>
  );
}

// ----------------------------------------------------------------------

Shadow.propTypes = {
  sx: PropTypes.object,
};

function Shadow({ sx, ...other }) {
  return (
    <Box
      sx={{
        left: 0,
        right: 0,
        bottom: 0,
        height: 24,
        zIndex: -1,
        m: 'auto',
        borderRadius: '50%',
        position: 'absolute',
        width: `calc(100% - 48px)`,
        // boxShadow: (theme) => theme.customShadows.z8,
        ...sx,
      }}
      {...other}
    />
  );
}
