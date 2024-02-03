import PropTypes from 'prop-types';
// @mui
import { List, Stack } from '@mui/material';
// locales
import { useLocales } from '../../../locales';
//
import { StyledSubheader } from './styles';
import NavList from './NavList';

// ----------------------------------------------------------------------

NavSectionVertical.propTypes = {
    sx: PropTypes.object,
    data: PropTypes.array,
};

export default function NavSectionVertical({ data, sx, ...other }) {
    const { translate } = useLocales();
    // console.log(data);
    return (
        <Stack sx={sx} {...other}>
            {data.map((group) => {
                const key = group.children?.[0]?.title;

                return (
                    <List key={key} disablePadding sx={{ px: 2 }}>
                        {group.children?.map((list) => (
                            <NavList key={list.title + list.path} data={list} depth={1}
                                hasChild={list.children.length === 0 ? false
                                    : true} />
                        ))}
                    </List>
                );
            })}
        </Stack>
    );
}
