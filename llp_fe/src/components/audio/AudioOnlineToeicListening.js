import { Box, Button, CardMedia, FormControl, MenuItem, Select } from '@mui/material'
import React, { useEffect, useState } from 'react'
import PropTypes from 'prop-types'

CustomAudioPlayer.propTypes = {
    mainAudio: PropTypes.string,
    subAudios: PropTypes.array,
    currentIndex: PropTypes.number,
    onSetCurrentIndex: PropTypes.func
}

export default function CustomAudioPlayer({ mainAudio, subAudios }) {

    const [play, setPlay] = useState(false)
    const [audio, setAudio] = useState();
    const [speed, setSpeed] = useState(1.0);

    const [currentTime, setCurrentTime] = useState(0)

    const handleSetSpeed = (e) => {
        const selectedSpeed = parseFloat(e.target.value);
        setSpeed(selectedSpeed);
        audio.playbackRate = selectedSpeed;
    }

    const handleSetTime = () => {
        setCurrentTime(audio?.currentTime.toFixed(2))
    }

    useEffect(() => {
        const audioTag = document.querySelector('#audio')
        setAudio(audioTag)
        audioTag.ontimeupdate = function () { handleSetTime() }
    }, [mainAudio]);


    return (

        <>
            <style>
                {
                    `
                .audio .MuiSelect-select {
                    padding:5px!important;
                    border-radius: 50%;
                }
                .audio .MuiSvgIcon-root.MuiSelect-icon {
                    display:none;
                }
                audio::-webkit-media-controls-panel {
                    background-color: #FFFFFF;
                  }
                  audio::-webkit-media-controls-timeline {
                    color:#158E4B
                  }
                `
                }
            </style>
            <Box sx={{ display: 'flex', flexDirection: 'row', background: '#FFFFFF', borderRadius: '20px', alignItems: 'center', justifyContent: 'space-between' }}>
                <CardMedia
                    component='audio'
                    id='audio'
                    src={`/listening/${mainAudio}`}
                    sx={{ width: 450, height: 52, background: 'transparent' }}
                    controls
                    controlsList='nodownload noplaybackrate'

                />

                <FormControl sx={{ width: 50, mr: 2, borderRadius: '50%' }}>
                    <Select
                        labelId="passageId"
                        id="passageId"
                        value={speed}
                        sx={{
                            ".MuiOutlinedInput-notchedOutline": {
                                border: `0px solid #FFFFFF`,
                            },
                            "&.MuiOutlinedInput-notchedOutline:hover": {
                                border: `0px solid #FFFFFF`,
                            },
                            "&.Mui-focused .MuiOutlinedInput-notchedOutline": {
                                border: `0px solid #FFFFFF`,
                            },
                            px: 0,
                            mx: 0
                        }}
                        className='audio'
                        onChange={handleSetSpeed}
                    >
                        <MenuItem value={0.25}>0.25x</MenuItem>
                        <MenuItem value={0.5}>0.5x</MenuItem>
                        <MenuItem value={0.75}>0.75x</MenuItem>
                        <MenuItem value={1}>1x</MenuItem>
                        <MenuItem value={1.25}>1.25x</MenuItem>
                        <MenuItem value={1.5}>1.5x</MenuItem>
                        <MenuItem value={1.75}>1.75x</MenuItem>
                        <MenuItem value={2}>x2</MenuItem>
                    </Select>
                </FormControl>
            </Box>
            {/* <Box>
                {currentTime}
            </Box> */}
        </>

    )
}
