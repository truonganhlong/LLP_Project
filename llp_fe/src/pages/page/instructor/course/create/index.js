
import InstructorMainLayout from '@/layouts/instructor';
import { PATH_PAGES } from '@/routes/paths';
import { Box, List, ListItem, ListItemText, ListSubheader, Tab, Tabs, Typography } from '@mui/material';
import { useRouter } from 'next/router';
import { useEffect, useState } from 'react';
import CheckCircleIcon from '@mui/icons-material/CheckCircle';
import PanoramaFishEyeIcon from '@mui/icons-material/PanoramaFishEye';
import TabPanel from '@/components/tabs/tabPanel';
import Instructor from '@/sections/@pages/instructor/course/intended';
import Curriculum from '@/sections/@pages/instructor/course/curriculum';
// ----------------------------------------------------------------------

CreateCourse.getLayout = (page) => <InstructorMainLayout> {page} </InstructorMainLayout>;

// ----------------------------------------------------------------------

const CustomCircleIcon = () => {
    return (
        <svg width="18" height="18" viewBox="0 0 72 72" fill="none" xmlns="http://www.w3.org/2000/svg">
            <path d="M1 36C1 55.33 16.67 71 36 71C55.33 71 71 55.33 71 36C71 16.67 55.33 1 36 1C16.67 1 1 16.67 1 36Z" stroke="black" />
        </svg>
    )
}

function a11yProps(index) {
    return {
        id: `vertical-tab-${index}`,
        'aria-controls': `vertical-tabpanel-${index}`,
    };
}

export default function CreateCourse() {

    const { push } = useRouter()

    const [valuePlan, setValuePlan] = useState(1);


    const handleChangePlan = (event, newValue) => {
        setValuePlan(newValue);
    };
    console.log(valuePlan);


    return (
        <>
            <style>
                {`
            .css-14v75vl-MuiButtonBase-root-MuiTab-root{
                min-height:fit-content!important;
                justify-content:flex-start!important;
                min-width:230px!important;
                .MuiTypography-subtitle1{
                    text-transform:none!important;
                }
                .MuiTypography-h5{
                    text-transform:none!important;
                }
            }
            .css-1xl3747-MuiTabs-indicator{
                right:none!important
            }
            
            `}
            </style>
            <Box sx={{ marginTop: '100px', marginLeft: '80px' }}>
                {/* <Box justifyContent='space-evenly'> */}
                <Box
                    sx={{ bgcolor: 'background.paper', display: 'flex' }}
                >
                    <Tabs
                        orientation="vertical"
                        value={valuePlan}
                        onChange={handleChangePlan}
                        aria-label="Vertical tabs example"
                        sx={{ borderColor: 'divider', width: '25%' }}
                        indicatorColor="secondary"
                        textColor="#2D2F31"
                    >
                        <Tab label={<Typography sx={{ justifyContent: 'flex-start', fontSize: '16px', fontWeight: '600', color: 'black', marginTop: '5px' }} >
                            Plan Your Course
                        </Typography>}
                            disabled />
                        <Tab icon={<CustomCircleIcon />} iconPosition="start" label={<Typography variant="subtitle1" sx={{ ml: '10px' }}>Intended learners</Typography>}  {...a11yProps(1)} />
                        <Tab icon={<CustomCircleIcon />} iconPosition="start" label={<Typography variant="subtitle1" sx={{ ml: '10px' }}>Course structure</Typography>} {...a11yProps(2)} />
                        <Tab icon={<CustomCircleIcon />} iconPosition="start" label={<Typography variant="subtitle1" sx={{ ml: '10px' }}>Setup & test video</Typography>} {...a11yProps(3)} />

                        <Tab label={<Typography sx={{ justifyContent: 'flex-start', fontSize: '16px', fontWeight: '700', color: 'black', marginTop: '15px' }} >
                            Create your content
                        </Typography>} disabled />

                        <Tab icon={<CustomCircleIcon />} iconPosition="start" label={<Typography variant="subtitle1" sx={{ ml: '10px' }}>Film & edit</Typography>}  {...a11yProps(5)} />
                        <Tab icon={<CustomCircleIcon />} iconPosition="start" label={<Typography variant="subtitle1" sx={{ ml: '10px' }}>Curriculum</Typography>}  {...a11yProps(6)} />
                        <Tab icon={<CustomCircleIcon />} iconPosition="start" label={<Typography variant="subtitle1" sx={{ ml: '10px' }}>{'Captions (optional)'}</Typography>}  {...a11yProps(7)} />
                        <Tab icon={<CustomCircleIcon />} iconPosition="start" label={<Typography variant="subtitle1" sx={{ ml: '10px' }}>{'Accessibility (optional)'}</Typography>}  {...a11yProps(8)} />

                        <Tab label={<Typography sx={{ justifyContent: 'flex-start', fontSize: '16px', fontWeight: '700', color: 'black', marginTop: '15px' }} >
                            Publish your course
                        </Typography>} disabled />

                        <Tab icon={<CustomCircleIcon />} iconPosition="start" label={<Typography variant="subtitle1" sx={{ ml: '10px' }}>Course landing page</Typography>}   {...a11yProps(10)} />
                        <Tab icon={<CustomCircleIcon />} iconPosition="start" label={<Typography variant="subtitle1" sx={{ ml: '10px' }}>Pricing</Typography>}  {...a11yProps(11)} />
                        <Tab icon={<CustomCircleIcon />} iconPosition="start" label={<Typography variant="subtitle1" sx={{ ml: '10px' }}>Promotions</Typography>}  {...a11yProps(12)} />
                        <Tab icon={<CustomCircleIcon />} iconPosition="start" label={<Typography variant="subtitle1" sx={{ ml: '10px' }}>Course message</Typography>}  {...a11yProps(13)} />

                    </Tabs>
                    <TabPanel sx={{ width: "70%" }} value={valuePlan} index={1}>
                        <Instructor />
                    </TabPanel>
                    <TabPanel value={valuePlan} index={2}>
                        Course structure
                    </TabPanel>
                    <TabPanel value={valuePlan} index={3}>
                        Setup & test video
                    </TabPanel>
                    {/* Create your content */}
                    <TabPanel value={valuePlan} index={5}>
                        Film & edit
                    </TabPanel>
                    <TabPanel sx={{ width: "70%" }} value={valuePlan} index={6}>
                        <Curriculum />
                    </TabPanel>
                    <TabPanel value={valuePlan} index={7}>
                        {'Captions (optional)'}
                    </TabPanel>
                    <TabPanel value={valuePlan} index={8}>
                        {'Accessibility (optional)'}
                    </TabPanel>

                    {/* Publish your course */}

                    <TabPanel value={valuePlan} index={10}>
                        Course landing page
                    </TabPanel>
                    <TabPanel value={valuePlan} index={11}>
                        Pricing
                    </TabPanel>
                    <TabPanel value={valuePlan} index={12}>
                        Promotions
                    </TabPanel>
                    <TabPanel value={valuePlan} index={13}>
                        Course message
                    </TabPanel>
                </Box>
                {/* </Box> */}
            </Box>
        </>
    );
}
