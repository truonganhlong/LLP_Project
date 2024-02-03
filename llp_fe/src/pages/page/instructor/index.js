
import InstructorMainLayout from '@/layouts/instructor';
import { PATH_PAGES } from '@/routes/paths';
import { Box, Typography } from '@mui/material';
import { useRouter } from 'next/router';
import { useEffect } from 'react';
// import { PATH_AUTH, PATH_DASHBOARD } from 'src/routes/paths';
// import LoadingScreen from 'src/components/loading-screen/LoadingScreen';

// ----------------------------------------------------------------------

Instructor.getLayout = (page) => <InstructorMainLayout> {page} </InstructorMainLayout>;

// ----------------------------------------------------------------------

export default function Instructor() {

    const { push } = useRouter()

    return (
        <>
            <Box direction="row" sx={{ marginLeft: '100px', marginTop: '60px' }}>
                <Typography sx={{ fontSize: '36px', fontFamily: 'Nunito' }} variant='h5'> Course</Typography>
            </Box>

        </>
    );
}
