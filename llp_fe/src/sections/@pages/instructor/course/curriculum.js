import { RHFTextField } from "@/components/hook-form";
import FormProvider from "@/components/hook-form/FormProvider";
import { Box, Button, Card, Container, Divider, Paper, TextField, Typography } from "@mui/material";
import { yupResolver } from "@hookform/resolvers/yup";
import * as Yup from 'yup';
import { useMemo, useState } from "react";
import { useForm } from "react-hook-form";
import Iconify from "@/components/iconify";

//-----------------------------------------------------
export default function Curriculum({ currentData }) {
    const [listWillLearn, setWillLearn] = useState([{ id: 1, content: '' }])
    const [listRequirement, setRequirement] = useState([{ id: 1, content: '' }])
    const [listPeople, setListPeople] = useState([{ id: 1, content: '' }])
    const [isVideo, setIsVideo] = useState(false)
    const [isDescription, setIsDescription] = useState(false)
    const [isCurriculum, setIsCurriculum] = useState(false)
    const [isLecture, setIsLecture] = useState(false)
    const [isQuiz, setIsQuiz] = useState(false)
    const [isAssignment, setIsAssignment] = useState(false)

    const validationSchema = Yup.object().shape({
        className: Yup.string().trim().required('Tên vai trò không được trống'),
        description: Yup.string().trim().max(255, "Tối đa 255 ký tự"),

    })

    const defaultValues = useMemo(
        () => ({

        }),
        [currentData]
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
                        Curriculum
                    </Typography>
                    <Divider />
                    <Box sx={{ width: '100%', padding: 2, borderRadius: '8px' }}>

                        {
                            listWillLearn.map((item, index) => (
                                <Box sx={{ display: 'flex', flexDirection: 'column', mb: 2, mr: 2, paddingTop: 3, paddingBottom: 3, justifyContent: 'space-evenly', border: '1px solid #000000' }} key={index}>

                                    <Box sx={{ display: 'flex', flexDirection: 'row', justifyContent: "space-around" }}>
                                        <Typography sx={{ fontSize: '16px', fontWeight: '600', marginLeft: '20px', mb: 4 }}>
                                            Section 1:
                                        </Typography>
                                        <RHFTextField
                                            id={`content${index}`}
                                            name={`content${index}`}
                                            label={`Lecture Introduction`}
                                            sx={{ mr: 6, width: '60%' }}
                                        />
                                    </Box>
                                </Box>
                            ))
                        }
                        <Button onClick={handleAddWillLearn} startIcon={<Iconify icon={'eva:plus-outline'} />} sx={{ height: 'fit-content', background: '#00AB5514!important', color: '#158E4B' }}>Add more to your response</Button>

                    </Box>


                </Card>
            </FormProvider>


            {/* </Paper> */}

        </Container>
    )
}