
import InstructorMainLayout from '@/layouts/instructor';
import { PATH_PAGES } from '@/routes/paths';
import { Box, Typography } from '@mui/material';
import { useRouter } from 'next/router';
import { useEffect } from 'react';
// ----------------------------------------------------------------------

CreateCourse.getLayout = (page) => <InstructorMainLayout> {page} </InstructorMainLayout>;

// ----------------------------------------------------------------------

export default function CreateCourse() {

    const { push } = useRouter()
    return (
        <>
            <Box  sx={{ marginTop: '60px', marginLeft: '100px' }}>
                <Typography sx={{ fontSize: '36px', fontFamily: 'Nunito' }} variant='h5'>
                    create Course
                </Typography>
            </Box>
        </>
    );
}
