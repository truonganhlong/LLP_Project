import PropTypes from 'prop-types';
import { memo } from 'react';
// @mui
import { Stack } from '@mui/material';
// utils
import { hideScrollbarY } from '../../../utils/cssStyles';
//
import NavList from './NavList';

// ----------------------------------------------------------------------

NavSectionHorizontal.propTypes = {
  sx: PropTypes.object,
  data: PropTypes.array,
};

function NavSectionHorizontal({ data, sx, ...other }) {

  return (
    <Stack
      direction="row"
      spacing={1}
      sx={{
        mx: 'auto',
        ...hideScrollbarY,
        ...sx,
        fontSize: 25,
        mr: 4,
        height: 60,
        alignItems: 'center'
      }}
      {...other}
    >
      {data.map((group) => (
        !group.subheader &&
        <Items key={group.subheader} items={group.items} />
      ))}
    </Stack>
  );
}

export default memo(NavSectionHorizontal);

// ----------------------------------------------------------------------

Items.propTypes = {
  items: PropTypes.array,
};

function Items({ items }) {

  return (
    <>
      {items.map((list) => (
        <NavList key={list.title + list.path} data={list} depth={1} hasChild={!!list.children} />
      ))}
    </>
  );
}
