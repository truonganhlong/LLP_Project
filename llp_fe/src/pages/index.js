
import { PATH_PAGES } from '@/routes/paths';
import { useRouter } from 'next/router';
import { useEffect } from 'react';
// import { PATH_AUTH, PATH_DASHBOARD } from 'src/routes/paths';
// import LoadingScreen from 'src/components/loading-screen/LoadingScreen';

// ----------------------------------------------------------------------

// HomePage.getLayout = (page) => <MainLayout> {page} </MainLayout>;

// ----------------------------------------------------------------------

export default function HomePage() {

    const { push } = useRouter()
    return (
        <>
        <a href={`${PATH_PAGES.instructor.root}`}>Instructor</a>
            
        </>
    );
}
