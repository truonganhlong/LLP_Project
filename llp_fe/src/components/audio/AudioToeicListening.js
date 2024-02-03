import { Box, Button, CardMedia, FormControl, MenuItem, Select } from '@mui/material'
import React, { useEffect, useState } from 'react'
import PropTypes from 'prop-types'

AudioListeningPlayer.propTypes = {
    mainAudio: PropTypes.string,
    subAudios: PropTypes.array,
    // currentIndex: PropTypes.number,
    // onSetCurrentIndex: PropTypes.func
}

export default function AudioListeningPlayer({ mainAudio, subAudios}) {

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
                .MuiSelect-select {
                    padding:5px!important;
                    border-radius: 50%;
                }
                .MuiSvgIcon-root.MuiSelect-icon {
                        display:none
                    }
             
                audio::-webkit-media-controls-panel {
                    background-color: #F3F5F2;
                  }
                  audio::-webkit-media-controls-timeline {
                    color:#158E4B
                  }
                `
                }
            </style>
            <Box sx={{ display: 'flex', flexDirection: 'row', background: '#F3F5F2', my: 2, borderRadius: '20px', alignItems: 'center', justifyContent: 'space-evenly',width:'570px' }}>
                <CardMedia
                    component='audio'
                    id='audio'
                    src={`/listening/${mainAudio}`}
                    sx={{ width: 450, background: 'transparent', ml: 2 }}
                    controls
                    controlsList='nodownload noplaybackrate'

                />

                <FormControl sx={{ width: 50, mr: 2, borderRadius: '50%' }}>
                    <Select
                        labelId="passageId"
                        id="passageId"
                        value={speed}
                        sx={{}}
                        onChange={handleSetSpeed}
                    >
                        <MenuItem value={0.25}>x0.25</MenuItem>
                        <MenuItem value={0.5}>x0.5</MenuItem>
                        <MenuItem value={0.75}>x0.75</MenuItem>
                        <MenuItem value={1}>x1</MenuItem>
                        <MenuItem value={1.25}>x1.25</MenuItem>
                        <MenuItem value={1.5}>x1.5</MenuItem>
                        <MenuItem value={1.75}>x1.75</MenuItem>
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
