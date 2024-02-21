import { RHFTextField } from "@/components/hook-form";
import FormProvider from "@/components/hook-form/FormProvider";
import { Box, Button, Card, Container, Divider, Paper, TextField, Typography } from "@mui/material";
import { yupResolver } from "@hookform/resolvers/yup";
import * as Yup from 'yup';
import { useMemo, useState } from "react";
import { useForm } from "react-hook-form";
import Iconify from "@/components/iconify";

//-----------------------------------------------------
export default function Instructor({ currentClass }) {
    const [listWillLearn, setWillLearn] = useState([{ id: 1, content: '' }])
    const [listRequirement, setRequirement] = useState([{ id: 1, content: '' }])
    const [listPeople, setListPeople] = useState([{ id: 1, content: '' }])

    const validationSchema = Yup.object().shape({
        className: Yup.string().trim().required('Tên vai trò không được trống'),
        description: Yup.string().trim().max(255, "Tối đa 255 ký tự"),

    })

    const defaultValues = useMemo(
        () => ({

        }),
        [currentClass]
    );

    const methods = useForm({
        resolver: yupResolver(validationSchema),
        defaultValues,
    });

    const {
        reset,
        watch,
        control,
        setValue,
        handleSubmit,
        formState: { errors, isSubmitting },
    } = methods;

    const handleAddWillLearn = () => {
        const obj = {
            id: listWillLearn[listWillLearn.length - 1].id + 1,
            content: '',
            is_true: 0
        }
        setWillLearn([
            ...listWillLearn,
            obj
        ])
    }

    const handleAddRequirement = () => {
        const obj = {
            id: listRequirement[listRequirement.length - 1].id + 1,
            content: '',
            is_true: 0
        }
        setRequirement([
            ...listRequirement,
            obj
        ])
    }

    const handleAddPeople = () => {
        const obj = {
            id: listPeople[listPeople.length - 1].id + 1,
            content: '',
            is_true: 0
        }
        setListPeople([
            ...listPeople,
            obj
        ])
    }

    const onSubmit = async (data) => { }

    return (
        <Container fixed sx={{ margin: 4, minWidth: '100%', height: 'fit-content' }}>
            {/* <Paper square elevation={3}  > */}
            <FormProvider methods={methods} onSubmit={handleSubmit(onSubmit)}>
                <Card sx={{ mt: 3, p: 3, mb: 5 }}>
                    <Typography sx={{ fontSize: '20px', marginLeft: '20px', mb: 4 }} variant='h5'>
                        Intended learners
                    </Typography>
                    <Divider />

                    <Typography sx={{ fontSize: '20px', marginLeft: '20px', mt: 4 }} variant='h5'>
                        What will students learn in your course ?
                    </Typography>
                    <Box sx={{ width: '90%', padding: 2, borderRadius: '8px' }}>

                        {
                            listWillLearn.map((item, index) => (
                                <Box sx={{ display: 'flex', flexDirection: 'column', mb: 2, mr: 4, justifyContent: 'space-evenly' }} key={index}>
                                    <RHFTextField
                                        id={`content${index}`}
                                        name={`content${index}`}
                                        label={`Example : Identify and manage project ricks`}
                                        sx={{ mr: 6 }}
                                    />
                                </Box>
                            ))
                        }
                        <Button onClick={handleAddWillLearn} startIcon={<Iconify icon={'eva:plus-outline'} />} sx={{ height: 'fit-content', background: '#00AB5514!important', color: '#158E4B' }}>Add more to your response</Button>

                    </Box>

                    <Typography sx={{ fontSize: '20px', marginLeft: '20px', mt: 4 }} variant='h5'>
                        What are the requirements or prerequisites for taking your course ?
                    </Typography>
                    <Box sx={{ width: '90%', padding: 2, borderRadius: '8px' }}>

                        {
                            listRequirement.map((item, index) => (
                                <Box sx={{ display: 'flex', flexDirection: 'column', mb: 2, mr: 4, justifyContent: 'space-evenly' }} key={index}>
                                    <RHFTextField
                                        id={`content${index}`}
                                        name={`content${index}`}
                                        label={`Example : No Programming experience needed. You will learn everything you need to know`}
                                        sx={{ mr: 6 }}
                                    />
                                </Box>
                            ))
                        }
                        <Button onClick={handleAddRequirement} startIcon={<Iconify icon={'eva:plus-outline'} />} sx={{ height: 'fit-content', background: '#00AB5514!important', color: '#158E4B' }}>Add more to your response</Button>

                    </Box>


                    <Typography sx={{ fontSize: '20px', marginLeft: '20px', mt: 4 }} variant='h5'>
                        Who is this course for ?
                    </Typography>
                    <Box sx={{ width: '90%', padding: 2, borderRadius: '8px' }}>

                        {
                            listPeople.map((item, index) => (
                                <Box sx={{ display: 'flex', flexDirection: 'column', mb: 2, mr: 4, justifyContent: 'space-evenly' }} key={index}>
                                    <RHFTextField
                                        id={`content${index}`}
                                        name={`content${index}`}
                                        label={`Example : Beginner Python developers curious about data science`}
                                        sx={{ mr: 6 }}
                                    />
                                </Box>
                            ))
                        }
                        <Button onClick={handleAddPeople} startIcon={<Iconify icon={'eva:plus-outline'} />} sx={{ height: 'fit-content', background: '#00AB5514!important', color: '#158E4B' }}>Add more to your response</Button>

                    </Box>

                </Card>
            </FormProvider>


            {/* </Paper> */}

        </Container>
    )
}