
import label from '@/components/label';
import SliderCustom from '@/components/slider-custom';
import MainLayout from '@/layouts/course';
import { PATH_PAGES } from '@/routes/paths';
import { Box, Button, Card, CardMedia, Container, Tab, Tabs, Typography } from '@mui/material';
import { useRouter } from 'next/router';
import { useEffect, useState } from 'react';
// import { PATH_AUTH, PATH_DASHBOARD } from 'src/routes/paths';
// import LoadingScreen from 'src/components/loading-screen/LoadingScreen';

// ----------------------------------------------------------------------

HomePage.getLayout = (page) => <MainLayout> {page} </MainLayout>;

// ----------------------------------------------------------------------

const topicsItem = [
  { value: 'python', label: (<p>Python</p>) },
  { value: 'javascript', label: (<p>Javascript</p>) },
  { value: 'flutter', label: (<p>Flutter</p>) },
  { value: 'reactjs', label: (<p>Reactjs</p>) },
  { value: 'css', label: (<p>Css</p>) },
]


export default function HomePage() {

  const { push } = useRouter()

  const [value, setValue] = useState('python');

  const handleChange = (event, newValue) => {
    setValue(newValue);
  };
  function SampleNextArrow(props) {
    const { className, style, onClick } = props
    return (
      <div
        className={`${className} ${'slick-next'}`}
        style={{ ...style }}
        onClick={onClick}
      />
    )
  }

  function SamplePrevArrow(props) {
    const { className, style, onClick } = props
    return (
      <div
        className={`${className} ${'slick-prev'}`}
        style={{ ...style }}
        onClick={onClick}
      />
    )
  }

  const settings = {
    dots: true,
    infinite: true,
    speed: 500,
    slidesToShow: 3,
    slidesToScroll: 1,
    width: 700
  };

  return (
    <>
      <style suppressHydrationWarning>
        {`
      .background-image {
        background: url('/image/homepage/intro1.png') no-repeat center center fixed; 
        -webkit-background-size: cover;
        -moz-background-size: cover;
        -o-background-size: cover;
        background-size: cover;
        height: 500px; 
        display: flex;
        align-items: center;
        justify-content: start; 
      }
      
      .text-over-image {
        color: black; 
      }

      .css-1aquho2-MuiTabs-indicator{
        top:50px!important;
        background-color:white!important
      }
      .css-1h9z7r5-MuiButtonBase-root-MuiTab-root.Mui-selected{
        color:black!important;
        font-weight:600;
        font-size:14px
      }
     
      `}
      </style>
      <Box sx={{ mt: 8.5, }} >
        <Box className="background-image" >
          <Card className="text-over-image" sx={{ ml: 15 }} >
            <Box sx={{ m: 4 }}>
              <Typography sx={{ fontSize: '25px' }} variant='h3'>Learning that gets you</Typography>
              <Typography sx={{ fontSize: '15px' }} variant='p'>Skills for your present (and your future). Get started with us.</Typography>
            </Box>
          </Card>

        </Box>
        <Box sx={{ m: 4 }}>
          <Box sx={{ width: '100%' }}>
            <Tabs value={value} onChange={handleChange}>
              {topicsItem.map((tab) => (
                <Tab value={tab.value} label={tab.label} key={tab.value} />
              ))}
            </Tabs>
          </Box>
          <Box sx={{ width: '95%', ml: 2, mr: 2, border: '1px solid', p: 2 }}>
            {value === 'python' ? <>
              <Box sx={{ width: '50%' }}>
                <Typography variant='h5' sx={{ paddingLeft: '20px', fontSize: '16px', fontWeight: '600' }}>
                  Expand your career opportunities with Python
                </Typography>
                <Typography variant='p' sx={{ textAlign: 'start' }}>Take one of Udemy’s range of Python courses and learn how to code using this incredibly useful language. Its simple syntax and readability makes Python perfect for Flask, Django, data science, and machine learning. You’ll learn how to build everything from games to sites to apps. Choose from a range of courses that will appeal to ...
                </Typography>
              </Box>
              <Button sx={{ m: 3, border: '1px solid #000000', color: '#000000' }}>Expand Python</Button>
              {/* <Box sx={{ width: '900px' }} className="slider-container">
                <SliderCustom {...settings}>
                  <div>
                    <h3>1</h3>
                  </div>
                  <div>
                    <h3>2</h3>
                  </div>
                  <div>
                    <h3>3</h3>
                  </div>
                  <div>
                    <h3>4</h3>
                  </div>
                  <div>
                    <h3>5</h3>
                  </div>
                  <div>
                    <h3>6</h3>
                  </div>

                </SliderCustom>
              </Box> */}
            </> : null}

          </Box>
        </Box>
      </Box>

    </>
  );
}
