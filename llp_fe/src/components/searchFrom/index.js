import { TextField } from '@mui/material';
import React from 'react'
import { useState } from 'react'
import Iconify from '../iconify';

export default function SearchBox() {

    const [isForcus, setIsForcus] = useState(false)

    const [keyword, setKeyword] = useState('');

    const handleSearch = (e) => {
        if (e.key === 'Enter') {
            if (parseInt(navigator.appVersion) < 4) return;

            let strFound = false;

            if (window.find) {
                // BROWSERS THAT SUPPORT window.find
                strFound = window.find(keyword);

                if (!strFound) {
                    strFound = window.find(keyword, 0, 1);
                    while (window.find(keyword, 0, 1)) continue;
                }
            } else if (navigator.appName.indexOf('Microsoft') !== -1) {
                // EXPLORER-SPECIFIC CODE
                let TRange = null;
                if (TRange !== null) {
                    TRange.collapse(false);
                    strFound = TRange.findText(keyword);
                    if (strFound) TRange.select();
                }
                if (TRange === null || strFound === 0) {
                    TRange = document.body.createTextRange();
                    strFound = TRange.findText(keyword);
                    if (strFound) TRange.select();
                }
            } else if (navigator.appName === 'Opera') {
                alert('Opera browsers not supported, sorry...');
                return;
            }

            if (!strFound) {
                alert("String '" + keyword + "' not found!");
                return;
            }
        }
    };

    return (
        <>
            <style>
                {
                    `
                    .MuiInputBase-input {
                        padding:9px!important;
                    }
                    .MuiOutlinedInput-notchedOutline {
                        border:none!important;
                    }
                `
                }
            </style>
            <TextField
                name='search'
                placeholder={isForcus ? "" : "Search"}
                InputProps={{
                    startAdornment: (
                        <Iconify icon="eva:search-outline" sx={{ width: 25, height: 25, color: '#158E4B' }} />
                    )
                }}
                sx={{
                    border: '1px solid #C3C3C3', borderRadius: '10px', width: 404, height: 41, padding: '0 !important', '& .css-1d3z3hw-MuiOutlinedInput-notchedOutline': {
                        border: 'none',

                    },
                    '& .css-ep72nq-MuiInputBase-input-MuiOutlinedInput-input': {
                        padding: 1
                    },
                    '& input::placeholder': {
                        color: '#857E7E', /* Thay đổi màu chữ của placeholder thành màu đỏ ví dụ */
                    }
                }}
                onFocus={() => setIsForcus(true)}
                onBlur={() => setIsForcus(false)}
                onChange={(e) => setKeyword(e.target.value)}
                onKeyDown={handleSearch}
            />
        </>

    )
}
