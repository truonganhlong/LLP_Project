import { Button } from '@mui/material'
import PropTypes from 'prop-types'
import React from 'react'


ButtonActiveCustomer.propTypes = {
  onFunction: PropTypes.func,
  text: PropTypes.string
}

export default function ButtonActiveCustomer({ text, onFunction }) {
  return (
    <Button disableRipple onClick={onFunction} sx={{
      background: '#158E4B!important',
      color: '#FCFCFC'
    }}>{text}</Button>
  )
}
