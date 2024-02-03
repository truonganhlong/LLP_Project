import PropTypes from 'prop-types';
// @mui
import { Dialog, Button, DialogTitle, DialogActions, DialogContent, useMediaQuery } from '@mui/material';
import { styled, useTheme } from '@mui/styles';

// ----------------------------------------------------------------------

ConfirmDialog.propTypes = {
  open: PropTypes.bool,
  title: PropTypes.node,
  action: PropTypes.node,
  content: PropTypes.node,
  onClose: PropTypes.func,
};


export default function ConfirmDialog({ title, content, action, open, onClose, ...other }) {
  const theme = useTheme();
  const isSmallScreen = useMediaQuery(theme.breakpoints.down('sm'));
  return (

    <Dialog fullScreen={isSmallScreen} open={open} onClose={onClose} {...other} >
      <DialogTitle sx={{ pb: 2, background: '#FCFCFC' }}>{title}</DialogTitle>

      {content && <DialogContent sx={{ typography: 'body', background: '#FCFCFC' }}> {content} </DialogContent>}

      <DialogActions>
        {action}

        <Button sx={{ background: '#F3F5F2!important', color: '#158E4b', width: 153, height: 35, }} onClick={onClose}>
          Close
        </Button>
      </DialogActions>
    </Dialog>
  );
}
