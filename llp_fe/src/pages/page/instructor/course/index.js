
import InstructorMainLayout from '@/layouts/instructor';
import { PATH_DASHBOARD, PATH_PAGES } from '@/routes/paths';
import { Box, Button, Tab, Tabs, Typography } from '@mui/material';
import { useRouter } from 'next/router';
import { useEffect, useState } from 'react';
// import { PATH_AUTH, PATH_DASHBOARD } from 'src/routes/paths';
// import LoadingScreen from 'src/components/loading-screen/LoadingScreen';
import PropTypes from 'prop-types';

// ----------------------------------------------------------------------

InstructorCourse.getLayout = (page) => <InstructorMainLayout> {page} </InstructorMainLayout>;

// ----------------------------------------------------------------------


function TabPanel(props) {
    const { children, value, index, ...other } = props;

    return (
        <div
            role="tabpanel"
            hidden={value !== index}
            id={`vertical-tabpanel-${index}`}
            aria-labelledby={`vertical-tab-${index}`}
            {...other}
        >
            {value === index && (
                <Box >
                    <Typography>{children}</Typography>
                </Box>
            )}
        </div>
    );
}

TabPanel.propTypes = {
    children: PropTypes.node,
    index: PropTypes.number.isRequired,
    value: PropTypes.number.isRequired,
};

function a11yProps(index) {
    return {
        id: `vertical-tab-${index}`,
        'aria-controls': `vertical-tabpanel-${index}`,
    };
}

export default function InstructorCourse() {
    const [value, setValue] = useState(0);

    const handleChange = (event, newValue) => {
        setValue(newValue);
    };

    const { push } = useRouter()

    return (
        <>
            <Box sx={{ marginTop: '60px', marginLeft: '100px' }}>
                <Typography sx={{ fontSize: '36px', fontFamily: 'Nunito' }} variant='h5'>
                    Courses
                </Typography>
                <Box>
                    <Button
                        onClick={() => {
                            push(`${PATH_PAGES.instructor.course.stepNew}`)
                        }}
                    >
                        New Course
                    </Button>
                </Box>
            </Box>

        </>
    );
}
