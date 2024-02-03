import PropTypes from 'prop-types';
// @mui
import { Box, Checkbox, TableRow, TableCell, TableHead, TableSortLabel } from '@mui/material';
import { useState } from 'react';

// ----------------------------------------------------------------------

const visuallyHidden = {
  border: 0,
  margin: -1,
  padding: 0,
  width: '1px',
  height: '1px',
  overflow: 'hidden',
  position: 'absolute',
  whiteSpace: 'nowrap',
  clip: 'rect(0 0 0 0)',
};

// ----------------------------------------------------------------------

TableHeadCustom.propTypes = {
  sx: PropTypes.object,
  onSort: PropTypes.func,
  orderBy: PropTypes.string,
  headLabel: PropTypes.array,
  rowCount: PropTypes.number,
  numSelected: PropTypes.number,
  onSelectAllRows: PropTypes.func,
  order: PropTypes.oneOf(['asc', 'desc']),
  onSortColumn: PropTypes.func,
  isTableBorder: PropTypes.bool,
  noCheckedAll: PropTypes.bool
};

export default function TableHeadCustom({
  order,
  orderBy,
  rowCount = 0,
  headLabel,
  numSelected = 0,
  onSort,
  onSortColumn,
  onSelectAllRows,
  isTableBorder,
  sx,
  className,
  noCheckedAll
}) {

  const [checked, setChecked] = useState(false)

  const handleChecked = (e) => {
    setChecked(!checked)
    onSelectAllRows(e)
  }
  return (
    <TableHead sx={sx} className={className}>
      <TableRow>
        {headLabel.map((headCell) => (
          <TableCell
            key={headCell.id}
            align={headCell.align || 'left'}
            sortDirection={orderBy === headCell.id ? order : false}
            sx={{
              width: headCell.width,
              minWidth: headCell.width,
              fontSize: '14px',
              color: '#158E4B',
              background: 'rgba(0, 171, 85, 0.12)',
              borderBottom: '1px solid #E3E3E3',
              paddingTop: '1px',
              paddingBottom: '1px',
            }}
          >
            {onSort ? (
              <TableSortLabel
                hideSortIcon={true}
                active={orderBy === headCell.id}
                direction={orderBy === headCell.id ? order : 'asc'}
                onClick={() => onSort(headCell.id)}
                sx={{ textTransform: 'capitalize' }}
              >
                {headCell.label}

                {orderBy === headCell.id ? (
                  <Box sx={{ ...visuallyHidden, }}>{order === 'desc' ? 'sorted descending' : 'sorted ascending'}</Box>
                ) : null}
              </TableSortLabel>
            ) : (
              headCell.label
            )}
          </TableCell>
        ))}
        {
          (noCheckedAll == false || noCheckedAll == undefined) &&
          <TableCell align="left" sx={{
            width: 5,
            minWidth: 5,
            background: 'rgba(0, 171, 85, 0.12)',
            borderBottom: '1px solid #E3E3E3',
          }}>
            <Checkbox
              color="primary"
              indeterminate={numSelected > 0 && numSelected < rowCount}
              checked={(numSelected > 0 && numSelected == rowCount)}
              onChange={handleChecked}
              inputProps={{
                'aria-label': 'select all desserts',
              }}
            />
          </TableCell>
        }
      </TableRow>
    </TableHead>
  );
}
