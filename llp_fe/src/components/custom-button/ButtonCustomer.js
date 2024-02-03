import PropTypes from 'prop-types'
import React from 'react'


ButtonCustomer.propTypes = {
    onFunction: PropTypes.func,
    text: PropTypes.string
}

export default function ButtonCustomer({ text, onFunction }) {
    return (
        <Button disableRipple onClick={onFunction} sx={{
            background: '#FCFCFC!important',
            color: '#158E4B'
        }}>{text}</Button>
    )
}
