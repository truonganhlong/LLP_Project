import PropTypes from 'prop-types';
import { forwardRef } from 'react';
// next
import NextLink from 'next/link';
// @mui
import { Box, Tooltip, ListItemText, Link } from '@mui/material';
// locales
import { useLocales } from '../../../locales';
// auth
import RoleBasedGuard from '../../../auth/RoleBasedGuard';
//
import Iconify from '../../iconify';
import { StyledItem, StyledIcon } from './styles';

// ----------------------------------------------------------------------

const NavItem = forwardRef(({ item, depth, open, active, isExternalLink, ...other }, ref) => {
  const { translate } = useLocales();

  const { title, path, icon, info, children, disabled, caption, roles } = item;

  const path_use = "/page" + path;

  const subItem = depth !== 1;

  const colorActive = '#158E4B';

  const renderContent = (
    <StyledItem ref={ref} open={open} depth={depth} active={active} disabled={disabled} {...other} disableRipple>

      <ListItemText
        primary={translate(title)}
        primaryTypographyProps={{
          noWrap: true,
          component: 'span',
          variant: active ? 'subtitle1' : 'body1',
        }}
        sx={{ color: (active ? colorActive : 'black'), '&:hover': { background: 'none', border: `1px solid ${colorActive}`, borderRadius: '5px' }, border: (active ? `1px solid ${colorActive}` : 'none'), borderRadius: (active ? '5px' : 'none'), fontWeight: '550', padding: '5px' }}
      />

      {info && (
        <Box component="span" sx={{ ml: 1, lineHeight: 0 }}>
          {info}
        </Box>
      )}

      {caption && (
        <Tooltip title={translate(caption)} arrow>
          <Box component="span" sx={{ ml: 0.5, lineHeight: 0 }}>
            <Iconify icon="eva:info-outline" width={16} />
          </Box>
        </Tooltip>
      )}

      {/*
       
      !!children && (
        <Iconify
          icon={subItem ? 'eva:chevron-right-fill' : 'eva:chevron-down-fill'}
          width={16}
          sx={{ ml: 0.5, flexShrink: 0 }}
        />
      )
      */}
    </StyledItem>
  );

  const renderItem = () => {
    // ExternalLink
    if (isExternalLink)
      return (
        <Link href={path_use} target="_blank" rel="noopener" underline="none">
          {renderContent}
        </Link>
      );

    // Default
    return (
      <NextLink href={path_use} passHref>
        {renderContent}
      </NextLink>
    );
  };

  return <RoleBasedGuard roles={roles}> {renderItem()} </RoleBasedGuard>;
});

NavItem.propTypes = {
  open: PropTypes.bool,
  active: PropTypes.bool,
  item: PropTypes.object,
  depth: PropTypes.number,
  isExternalLink: PropTypes.bool,
};

export default NavItem;
