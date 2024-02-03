// @mui
import { alpha, styled } from '@mui/material/styles';
import { ListItemIcon, ListSubheader, ListItemButton } from '@mui/material';
// config
import { ICON, NAV } from '../../../config';

// ----------------------------------------------------------------------

export const StyledItem = styled(ListItemButton, {
  shouldForwardProp: (prop) => prop !== 'active' && prop !== 'caption',
})(({ active, disabled, depth, caption, theme, active2 }) => {


  const subItem = depth !== 1;

  const activeStyle = {
    color: theme.palette.text.primary,
    backgroundColor: 'transparent',
  };

  const activeSubStyle = {
    color: theme.palette.primary.main,
    backgroundColor: alpha(theme.palette.primary.main, theme.palette.action.selectedOpacity),
  };

  return {
    position: 'relative',
    textTransform: 'capitalize',
    paddingLeft: theme.spacing(2),
    paddingRight: theme.spacing(0.5),
    marginBottom: theme.spacing(1),
    marginBottom: theme.spacing(0.5),
    color: theme.palette.text.secondary,
    borderRadius: theme.shape.borderRadius,
    height: NAV.H_DASHBOARD_ITEM,
    // Sub item
    ...(subItem && {
      height: NAV.H_DASHBOARD_ITEM_SUB,
      paddingLeft: theme.spacing(3),
      ...(depth > 2 && {
        paddingLeft: theme.spacing(depth + 1),
      }),
      ...(caption && {
        height: NAV.H_DASHBOARD_ITEM,
      }),
    }),
    // Active item
    ...(active && {
      ...activeStyle,
      '&:hover': {
        ...activeSubStyle,
      },
    }),
    // Active sub item
    ...(subItem &&
      active && active2 == 'true' && {
      ...activeSubStyle,
      '&:hover': {
        ...activeSubStyle,
      },
    }),
    // Disabled
    ...(disabled && {
      '&.Mui-disabled': {
        opacity: 0.64,
      },
    }),
  };
});

// ----------------------------------------------------------------------

export const StyledIcon = styled(ListItemIcon)({
  display: 'flex',
  alignItems: 'center',
  justifyContent: 'center',
  width: 2,
  height: ICON.NAV_ITEM,
});

// ----------------------------------------------------------------------

export const StyledDotIcon = styled('span', {
  shouldForwardProp: (prop) => prop !== 'active',
})(({ active, theme }) => ({
  width: 4,
  height: 4,
  borderRadius: '50%',
  backgroundColor: theme.palette.text.disabled,
  transition: theme.transitions.create('transform', {
    duration: theme.transitions.duration.shorter,
  }),
  ...(active && {
    transform: 'scale(2)',
    backgroundColor: theme.palette.primary.main,
  }),
}));

// ----------------------------------------------------------------------

export const StyledSubheader = styled(ListSubheader)(({ theme }) => ({
  ...theme.typography.overline,
  fontSize: 11,
  paddingTop: theme.spacing(3),
  paddingBottom: theme.spacing(1),
  color: theme.palette.text.secondary,
}));
