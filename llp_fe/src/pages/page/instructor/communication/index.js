
import InstructorMainLayout from '@/layouts/instructor';
import { PATH_PAGES } from '@/routes/paths';
import { Box, Typography } from '@mui/material';
import { useRouter } from 'next/router';
import { useEffect } from 'react';
// import { PATH_AUTH, PATH_DASHBOARD } from 'src/routes/paths';
// import LoadingScreen from 'src/components/loading-screen/LoadingScreen';

// ----------------------------------------------------------------------

InstructorCommunication.getLayout = (page) => <InstructorMainLayout> {page} </InstructorMainLayout>;

// ----------------------------------------------------------------------

export default function InstructorCommunication({value}) {

    const { push } = useRouter()

    return (
        <>
            <Box>
                <Typography sx={{ fontSize: '36px', fontFamily: 'Nunito' }} variant='h5'>
                Communication
                </Typography>
            </Box>

        </>
    );
}
