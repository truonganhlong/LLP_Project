// ----------------------------------------------------------------------

import { is } from 'date-fns/locale';

function path(root, sublink) {
    return `${root}${sublink}`;
}

const ROOTS_AUTH = '/auth';
const ROOTS_DASHBOARD = '/dashboard';
const ROOTS_PAGE = '/page'
// ----------------------------------------------------------------------

export const PATH_AUTH = {
    root: ROOTS_AUTH,
    login: path(ROOTS_AUTH, '/login'),
    loginAdmin: path(ROOTS_AUTH, '/loginAdmin'),
    register: path(ROOTS_AUTH, '/register'),
    loginUnprotected: path(ROOTS_AUTH, '/login-unprotected'),
    registerUnprotected: path(ROOTS_AUTH, '/register-unprotected'),
    verify: path(ROOTS_AUTH, '/verify'),
    resetPassword: path(ROOTS_AUTH, '/reset-password'),
    newPassword: path(ROOTS_AUTH, '/new-password'),
};

export const PATH_PAGE = {
    comingSoon: '/coming-soon',
    maintenance: '/maintenance',
    about: '/about-us',
    faqs: '/faqs',
    page403: '/403',
    page404: '/404',
    page500: '/500',
};

export const PATH_PAGES = {
    root: ROOTS_PAGE,
    home: path(ROOTS_PAGE, '/home'),
    account: path(ROOTS_PAGE, "/user/account"),
    instructor: {
        root: path(ROOTS_PAGE, '/instructor'),
    },
    onlinetest: {
        root: path(ROOTS_PAGE, '/onlinetest'),
        ielttest: {
            root: path(ROOTS_PAGE, '/onlinetest/ielttest'),

            reading: {
                root: path(ROOTS_PAGE, '/onlinetest/ielttest/reading'),
                cambridge: {
                    root: path(ROOTS_PAGE, '/onlinetest/ielttest/reading/cambridge'),
                    passage: {
                        root: path(ROOTS_PAGE, '/onlinetest/ielttest/reading/cambridge'),
                        // new: path(ROOTS_DASHBOARD, '/course/reading/cambridge/passage/new'),
                        // edit: path(ROOTS_DASHBOARD, `/course/reading/cambridge/passage/edit`),
                    },
                    dofulltest: {
                        root: path(ROOTS_PAGE, '/onlinetest/ielttest/reading/cambridge/dofulltest'),
                        result: path(ROOTS_PAGE, '/onlinetest/ielttest/reading/cambridge/dofulltest/result')
                    }
                    // vocabulary: {
                    //     root: path(ROOTS_DASHBOARD, '/course/reading/cambridge/vocabulary'),
                    //     new: path(ROOTS_DASHBOARD, '/course/reading/cambridge/vocabulary/new'),
                    //     edit: path(ROOTS_DASHBOARD, `/course/reading/cambridge/vocabulary/edit`),
                    // }
                },
            },

            listening: {
                root: path(ROOTS_PAGE, '/onlinetest/ielttest/listening'),
                cambridge: {
                    root: path(ROOTS_PAGE, '/onlinetest/ielttest/listening/cambridge'),
                    passage: {
                        root: path(ROOTS_PAGE, '/onlinetest/ielttest/listening/cambridge'),
                        // new: path(ROOTS_DASHBOARD, '/course/reading/cambridge/passage/new'),
                        // edit: path(ROOTS_DASHBOARD, `/course/reading/cambridge/passage/edit`),
                    },
                    dofulltest: {
                        root: path(ROOTS_PAGE, '/onlinetest/ielttest/listening/cambridge/dofulltest'),
                        result: path(ROOTS_PAGE, '/onlinetest/ielttest/listening/cambridge/dofulltest/result')
                    }
                    // vocabulary: {
                    //     root: path(ROOTS_DASHBOARD, '/course/reading/cambridge/vocabulary'),
                    //     new: path(ROOTS_DASHBOARD, '/course/reading/cambridge/vocabulary/new'),
                    //     edit: path(ROOTS_DASHBOARD, `/course/reading/cambridge/vocabulary/edit`),
                    // }
                },
            },

            result: {
                root: path(ROOTS_PAGE, '/onlinetest/ielttest/result'),
            }
        },
        toeic: {
            root: path(ROOTS_PAGE, '/onlinetest/testtoeic'),

            reading: {
                root: path(ROOTS_PAGE, '/onlinetest/testtoeic/reading'),
                ets: {
                    root: path(ROOTS_PAGE, '/onlinetest/testtoeic/reading/ets'),
                    test: {
                        root: path(ROOTS_PAGE, '/onlinetest/testtoeic/reading/ets/test'),
                        dofulltest: {
                            root: path(ROOTS_PAGE, '/onlinetest/testtoeic/reading/ets/test/dofulltest'),
                            result: path(ROOTS_PAGE, '/onlinetest/testtoeic/reading/ets/test/dofulltest/result')
                        }
                    }
                },
            },

            listening: {
                root: path(ROOTS_PAGE, '/onlinetest/testtoeic/listening'),
                ets: {
                    root: path(ROOTS_PAGE, '/onlinetest/testtoeic/listening/ets'),
                    test: {
                        root: path(ROOTS_PAGE, '/onlinetest/testtoeic/listening/ets/test'),
                        dofulltest: {
                            root: path(ROOTS_PAGE, '/onlinetest/testtoeic/listening/ets/test/dofulltest'),
                            result: path(ROOTS_PAGE, '/onlinetest/testtoeic/listening/ets/test/dofulltest/result')
                        }
                    }
                },
            },
        },
        grammar: {
            root: path(ROOTS_PAGE, '/onlinetest/testgrammar'),
            test: {
                root: path(ROOTS_PAGE, '/onlinetest/testgrammar/test'),
                dofulltest: {
                    root: path(ROOTS_PAGE, '/onlinetest/testgrammar/test/dofulltest'),
                    result: path(ROOTS_PAGE, '/onlinetest/testgrammar/test/dofulltest/result'),
                }
            }
        },

    },
    toeic: {
        root: path(ROOTS_PAGE, '/toeic'),
        listening: {
            root: path(ROOTS_PAGE, '/toeic/listening')
        }
    },

    grammar: {
        root: path(ROOTS_PAGE, '/grammar'),
    }
}

export const PATH_DASHBOARD = {
    root: ROOTS_DASHBOARD,
    home: path(ROOTS_DASHBOARD, '/home'),
    fileManager: path(ROOTS_DASHBOARD, '/files-manager'),
    permissionDenied: path(ROOTS_DASHBOARD, '/permission-denied'),
    blank: path(ROOTS_DASHBOARD, '/blank'),
    page403: path(ROOTS_DASHBOARD, "/403"),

    reading: {
        root: path(ROOTS_DASHBOARD, '/course/reading'),
        introduction: {
            root: path(ROOTS_DASHBOARD, '/course/reading/introduction'),
            edit: path(ROOTS_DASHBOARD, '/course/reading/introduction/edit'),
            new: path(ROOTS_DASHBOARD, '/course/reading/introduction/new'),

        },

        cambridge: {
            passage: {
                root: path(ROOTS_DASHBOARD, '/course/reading/cambridge/passage'),
                new: path(ROOTS_DASHBOARD, '/course/reading/cambridge/passage/new'),
                edit: path(ROOTS_DASHBOARD, `/course/reading/cambridge/passage/edit`),
            },
            vocabulary: {
                root: path(ROOTS_DASHBOARD, '/course/reading/cambridge/vocabulary'),
                new: path(ROOTS_DASHBOARD, '/course/reading/cambridge/vocabulary/new'),
                edit: path(ROOTS_DASHBOARD, `/course/reading/cambridge/vocabulary/edit`),
            },
            lession: {
                root: path(ROOTS_DASHBOARD, '/course/reading/cambridge/lession'),
                new: path(ROOTS_DASHBOARD, '/course/reading/cambridge/lession/new'),
                edit: path(ROOTS_DASHBOARD, `/course/reading/cambridge/lession/edit`),
            },
        },
        video: {
            content: {
                root: path(ROOTS_DASHBOARD, '/course/reading/video/content'),
                new: path(ROOTS_DASHBOARD, '/course/reading/video/content/new'),
                edit: path(ROOTS_DASHBOARD, `/course/reading/video/content/edit`),
            },
            lesson: {
                root: path(ROOTS_DASHBOARD, '/course/reading/video/lesson'),
            }
        },

        realtest: {
            passage: {
                root: path(ROOTS_DASHBOARD, '/course/reading/realTest/passage'),
                new: path(ROOTS_DASHBOARD, '/course/reading/realTest/passage/new'),
                edit: path(ROOTS_DASHBOARD, `/course/reading/realTest/passage/edit`),
            },
            vocabulary: {
                root: path(ROOTS_DASHBOARD, '/course/reading/realTest/vocabulary'),
                new: path(ROOTS_DASHBOARD, '/course/reading/realTest/vocabulary/new'),
                edit: path(ROOTS_DASHBOARD, `/course/reading/realTest/vocabulary/edit`),
            },
            lession: {
                root: path(ROOTS_DASHBOARD, '/course/reading/realTest/lession'),
                new: path(ROOTS_DASHBOARD, '/course/reading/realTest/lession/new'),
                edit: path(ROOTS_DASHBOARD, `/course/reading/realTest/lession/edit`),
            },
        },

    },

    speaking: {
        part1: {
            suggestion: {
                root: path(ROOTS_DASHBOARD, '/course/speaking/part1/suggestion'),
                new: path(ROOTS_DASHBOARD, '/course/speaking/part1/suggestion/new'),
                edit: path(ROOTS_DASHBOARD, '/course/speaking/part1/suggestion/edit')
            },
            layout: {
                root: path(ROOTS_DASHBOARD, '/course/speaking/part1/layout'),
                edit: path(ROOTS_DASHBOARD, '/course/speaking/part1/layout/edit'),

            },
            vocabulary: {
                root: path(ROOTS_DASHBOARD, '/course/speaking/part1/vocabulary'),
                new: path(ROOTS_DASHBOARD, '/course/speaking/part1/vocabulary/new'),
                edit: path(ROOTS_DASHBOARD, '/course/speaking/part1/vocabulary/edit')
            },
        },
        part2: {
            suggestion: {
                root: path(ROOTS_DASHBOARD, '/course/speaking/part2/suggestion'),
                new: path(ROOTS_DASHBOARD, '/course/speaking/part2/suggestion/new'),
                edit: path(ROOTS_DASHBOARD, '/course/speaking/part2/suggestion/edit')
            },
            vocabulary: {
                root: path(ROOTS_DASHBOARD, '/course/speaking/part2/vocabulary'),
                new: path(ROOTS_DASHBOARD, '/course/speaking/part2/vocabulary/new'),
                edit: path(ROOTS_DASHBOARD, '/course/speaking/part2/vocabulary/edit')
            },
            layout: {
                root: path(ROOTS_DASHBOARD, '/course/speaking/part2/layout'),
                edit: path(ROOTS_DASHBOARD, '/course/speaking/part2/layout/edit'),
            }
        },

    },

    writing: {
        task1: {
            suggestion: {
                root: path(ROOTS_DASHBOARD, '/course/writing/task1/suggestion'),
                new: path(ROOTS_DASHBOARD, '/course/writing/task1/suggestion/new'),
                edit: path(ROOTS_DASHBOARD, '/course/writing/task1/suggestion/edit')
            },
            layout: {
                root: path(ROOTS_DASHBOARD, '/course/writing/task1/layout'),
                edit: (id) => path(ROOTS_DASHBOARD, `/course/writing/task1/layout/${id}`),
                detail: path(ROOTS_DASHBOARD, "/course/writing/task1/layout/detail")
            }
        },
        task2: {
            suggestion: {
                root: path(ROOTS_DASHBOARD, '/course/writing/task2/suggestion'),
                new: path(ROOTS_DASHBOARD, '/course/writing/task2/suggestion/new'),
                edit: path(ROOTS_DASHBOARD, '/course/writing/task2/suggestion/edit')
            },
            layout: {
                root: path(ROOTS_DASHBOARD, '/course/writing/task2/layout'),
                edit: (id) => path(ROOTS_DASHBOARD, `/course/writing/task2/layout/${id}`),
                detail: path(ROOTS_DASHBOARD, "/course/writing/task2/layout/detail")
            }
        },
    },

    listening: {
        root: path(ROOTS_DASHBOARD, '/course/listening'),
        introduction: {
            root: path(ROOTS_DASHBOARD, '/course/listening/introduction'),
            edit: path(ROOTS_DASHBOARD, '/course/listening/introduction/edit'),
            new: path(ROOTS_DASHBOARD, '/course/listening/introduction/new'),
        },
        cambridge: {
            content: {
                type: {
                    root: path(ROOTS_DASHBOARD, '/course/listening/cambridge/content/type'),
                    new: path(ROOTS_DASHBOARD, '/course/listening/cambridge/content/type/new'),
                    edit: path(ROOTS_DASHBOARD, '/course/listening/cambridge/content/type/edit'),
                },
                read: {
                    root: path(ROOTS_DASHBOARD, '/course/listening/cambridge/content/read'),
                    new: path(ROOTS_DASHBOARD, '/course/listening/cambridge/content/read/new'),
                    edit: path(ROOTS_DASHBOARD, '/course/listening/cambridge/content/read/edit'),
                }
            },
            lession: {
                root: path(ROOTS_DASHBOARD, '/course/listening/cambridge/lesson'),
            },

        },
        videoLecture: {
            content: {
                root: path(ROOTS_DASHBOARD, '/course/listening/video/content'),
                new: path(ROOTS_DASHBOARD, '/course/listening/video/content/new'),
                edit: path(ROOTS_DASHBOARD, '/course/listening/video/content/edit'),
            },
            lesson: {
                root: path(ROOTS_DASHBOARD, '/course/listening/video/lesson'),
            }
        }

    },

    toeic: {
        reading: {
            ets2020: {
                content: {
                    root: path(ROOTS_DASHBOARD, '/toeic/reading/ets2020/content'),
                    new: path(ROOTS_DASHBOARD, '/toeic/reading/ets2020/content/new'),
                    edit: path(ROOTS_DASHBOARD, '/toeic/reading/ets2020/content/edit'),
                    detail: path(ROOTS_DASHBOARD, '/toeic/reading/ets2020/content/detail'),
                    detailEdit: path(ROOTS_DASHBOARD, '/toeic/reading/ets2020/content/detail/edit')
                },
                lesson: {
                    root: path(ROOTS_DASHBOARD, '/toeic/reading/ets2020/lesson'),
                }
            },
            ets2021: {
                content: {
                    root: path(ROOTS_DASHBOARD, '/toeic/reading/ets2021/content'),
                    new: path(ROOTS_DASHBOARD, '/toeic/reading/ets2021/content/new'),
                    edit: path(ROOTS_DASHBOARD, '/toeic/reading/ets2021/content/edit'),
                    detail: path(ROOTS_DASHBOARD, '/toeic/reading/ets2021/content/detail'),
                    detailEdit: path(ROOTS_DASHBOARD, '/toeic/reading/ets2021/content/detail/edit')
                },
                lesson: {
                    root: path(ROOTS_DASHBOARD, '/toeic/reading/ets2021/lesson'),
                }
            },
            ets2022: {
                content: {
                    root: path(ROOTS_DASHBOARD, '/toeic/reading/ets2022/content'),
                    new: path(ROOTS_DASHBOARD, '/toeic/reading/ets2022/content/new'),
                    edit: path(ROOTS_DASHBOARD, '/toeic/reading/ets2022/content/edit'),
                    detail: path(ROOTS_DASHBOARD, '/toeic/reading/ets2022/content/detail'),
                    detailEdit: path(ROOTS_DASHBOARD, '/toeic/reading/ets2022/content/detail/edit')
                },
                lesson: {
                    root: path(ROOTS_DASHBOARD, '/toeic/reading/ets2022/lesson'),
                }
            },
            ets2023: {
                content: {
                    root: path(ROOTS_DASHBOARD, '/toeic/reading/ets2023/content'),
                    new: path(ROOTS_DASHBOARD, '/toeic/reading/ets2023/content/new'),
                    edit: path(ROOTS_DASHBOARD, '/toeic/reading/ets2023/content/edit'),
                    detail: path(ROOTS_DASHBOARD, '/toeic/reading/ets2023/content/detail'),
                    detailEdit: path(ROOTS_DASHBOARD, '/toeic/reading/ets2023/content/detail/edit')
                },
                lesson: {
                    root: path(ROOTS_DASHBOARD, '/toeic/reading/ets2023/lesson'),
                }
            },
            introduction: {
                root: path(ROOTS_DASHBOARD, '/toeic/reading/introduction'),
                edit: path(ROOTS_DASHBOARD, '/toeic/reading/introduction/edit'),
                new: path(ROOTS_DASHBOARD, '/toeic/reading/introduction/new'),
            },
            videoLecture: {
                content: {
                    root: path(ROOTS_DASHBOARD, '/toeic/reading/video/content'),
                    new: path(ROOTS_DASHBOARD, '/toeic/reading/video/content/new'),
                    edit: path(ROOTS_DASHBOARD, '/toeic/reading/video/content/edit'),
                },
                lesson: {
                    root: path(ROOTS_DASHBOARD, '/toeic/reading/video/lesson'),
                }
            }
        },
        listening: {
            ets2020: {
                content: {
                    root: path(ROOTS_DASHBOARD, '/toeic/listening/ets2020/content'),
                    new: path(ROOTS_DASHBOARD, '/toeic/listening/ets2020/content/new'),
                    edit: path(ROOTS_DASHBOARD, '/toeic/listening/ets2020/content/edit'),
                    detail: path(ROOTS_DASHBOARD, '/toeic/listening/ets2020/content/detail'),
                    detailEdit: path(ROOTS_DASHBOARD, '/toeic/listening/ets2020/content/detail/edit')
                },
                lesson: {
                    root: path(ROOTS_DASHBOARD, '/toeic/listening/ets2020/lesson'),
                }
            },
            ets2021: {
                content: {
                    root: path(ROOTS_DASHBOARD, '/toeic/listening/ets2021/content'),
                    new: path(ROOTS_DASHBOARD, '/toeic/listening/ets2021/content/new'),
                    edit: path(ROOTS_DASHBOARD, '/toeic/listening/ets2021/content/edit'),
                    detail: path(ROOTS_DASHBOARD, '/toeic/listening/ets2021/content/detail'),
                    detailEdit: path(ROOTS_DASHBOARD, '/toeic/listening/ets2021/content/detail/edit')
                },
                lesson: {
                    root: path(ROOTS_DASHBOARD, '/toeic/listening/ets2021/lesson'),
                }
            },
            ets2022: {
                content: {
                    root: path(ROOTS_DASHBOARD, '/toeic/listening/ets2022/content'),
                    new: path(ROOTS_DASHBOARD, '/toeic/listening/ets2022/content/new'),
                    edit: path(ROOTS_DASHBOARD, '/toeic/listening/ets2022/content/edit'),
                    detail: path(ROOTS_DASHBOARD, '/toeic/listening/ets2022/content/detail'),
                    detailEdit: path(ROOTS_DASHBOARD, '/toeic/listening/ets2022/content/detail/edit')
                },
                lesson: {
                    root: path(ROOTS_DASHBOARD, '/toeic/listening/ets2022/lesson'),
                }
            },
            ets2023: {
                content: {
                    root: path(ROOTS_DASHBOARD, '/toeic/listening/ets2023/content'),
                    new: path(ROOTS_DASHBOARD, '/toeic/listening/ets2023/content/new'),
                    edit: path(ROOTS_DASHBOARD, '/toeic/listening/ets2023/content/edit'),
                    detail: path(ROOTS_DASHBOARD, '/toeic/listening/ets2023/content/detail'),
                    detailEdit: path(ROOTS_DASHBOARD, '/toeic/listening/ets2023/content/detail/edit')
                },
                lesson: {
                    root: path(ROOTS_DASHBOARD, '/toeic/listening/ets2023/lesson'),
                }
            },
            introduction: {
                root: path(ROOTS_DASHBOARD, '/toeic/listening/introduction'),
                edit: path(ROOTS_DASHBOARD, '/toeic/listening/introduction/edit'),
                new: path(ROOTS_DASHBOARD, '/toeic/listening/introduction/new'),
            },
            videoLecture: {
                content: {
                    root: path(ROOTS_DASHBOARD, '/toeic/listening/video/content'),
                    new: path(ROOTS_DASHBOARD, '/toeic/listening/video/content/new'),
                    edit: path(ROOTS_DASHBOARD, '/toeic/listening/video/content/edit'),
                },
                lesson: {
                    root: path(ROOTS_DASHBOARD, '/toeic/listening/video/lesson'),
                }
            }
        }
    },

    onlinetest: {
        ielt: {
            reading: {
                root: path(ROOTS_DASHBOARD, '/onlinetest/ielts/reading/cambridge'),
                detail: path(ROOTS_DASHBOARD, '/onlinetest/ielts/reading/cambridge/details'),
                new: path(ROOTS_DASHBOARD, '/onlinetest/ielts/reading/cambridge/new'),
                edit: path(ROOTS_DASHBOARD, '/onlinetest/ielts/reading/cambridge/edit'),
                lesson: {
                    root: path(ROOTS_DASHBOARD, '/onlinetest/ielts/reading/lesson')
                }
            },
            listening: {
                root: path(ROOTS_DASHBOARD, '/onlinetest/ielts/listening/cambridge'),
                detail: path(ROOTS_DASHBOARD, '/onlinetest/ielts/listening/cambridge/details'),
                new: path(ROOTS_DASHBOARD, '/onlinetest/ielts/listening/cambridge/new'),
                edit: path(ROOTS_DASHBOARD, '/onlinetest/ielts/listening/cambridge/edit'),
                lesson: {
                    root: path(ROOTS_DASHBOARD, '/onlinetest/ielts/listening/lesson')
                }
            }
        },
        toeic: {
            reading: {
                ets2020: {
                    lesson: {
                        root: path(ROOTS_DASHBOARD, '/onlinetest/testtoeic/reading/ets2020/lesson')
                    },
                    test: {
                        root: path(ROOTS_DASHBOARD, '/onlinetest/testtoeic/reading/ets2020/test'),
                        add: {
                            root: path(ROOTS_DASHBOARD, '/onlinetest/testtoeic/reading/ets2020/test/new')
                        },
                        edit: {
                            root: path(ROOTS_DASHBOARD, '/onlinetest/testtoeic/reading/ets2020/test/edit')
                        },
                        detail: {
                            root: path(ROOTS_DASHBOARD, '/onlinetest/testtoeic/reading/ets2020/test/details')
                        }
                    }
                },
                ets2021: {
                    lesson: {
                        root: path(ROOTS_DASHBOARD, '/onlinetest/testtoeic/reading/ets2021/lesson')
                    },
                    test: {
                        root: path(ROOTS_DASHBOARD, '/onlinetest/testtoeic/reading/ets2021/test'),
                        add: {
                            root: path(ROOTS_DASHBOARD, '/onlinetest/testtoeic/reading/ets2021/test/new')
                        },
                        edit: {
                            root: path(ROOTS_DASHBOARD, '/onlinetest/testtoeic/reading/ets2021/test/edit')
                        },
                        detail: {
                            root: path(ROOTS_DASHBOARD, '/onlinetest/testtoeic/reading/ets2021/test/details')
                        }
                    }
                },
                ets2022: {
                    lesson: {
                        root: path(ROOTS_DASHBOARD, '/onlinetest/testtoeic/reading/ets2022/lesson')
                    },
                    test: {
                        root: path(ROOTS_DASHBOARD, '/onlinetest/testtoeic/reading/ets2022/test'),
                        add: {
                            root: path(ROOTS_DASHBOARD, '/onlinetest/testtoeic/reading/ets2022/test/new')
                        },
                        edit: {
                            root: path(ROOTS_DASHBOARD, '/onlinetest/testtoeic/reading/ets2022/test/edit')
                        },
                        detail: {
                            root: path(ROOTS_DASHBOARD, '/onlinetest/testtoeic/reading/ets2022/test/details')
                        }
                    }
                },
                ets2023: {
                    lesson: {
                        root: path(ROOTS_DASHBOARD, '/onlinetest/testtoeic/reading/ets2023/lesson')
                    },
                    test: {
                        root: path(ROOTS_DASHBOARD, '/onlinetest/testtoeic/reading/ets2023/test'),
                        add: {
                            root: path(ROOTS_DASHBOARD, '/onlinetest/testtoeic/reading/ets2023/test/new')
                        },
                        edit: {
                            root: path(ROOTS_DASHBOARD, '/onlinetest/testtoeic/reading/ets2023/test/edit')
                        },
                        detail: {
                            root: path(ROOTS_DASHBOARD, '/onlinetest/testtoeic/reading/ets2023/test/details')
                        }
                    }
                }
            },
            listening: {
                ets2020: {
                    lesson: {
                        root: path(ROOTS_DASHBOARD, '/onlinetest/testtoeic/listening/ets2020/lesson')
                    },
                    test: {
                        root: path(ROOTS_DASHBOARD, '/onlinetest/testtoeic/listening/ets2020/test'),
                        add: {
                            root: path(ROOTS_DASHBOARD, '/onlinetest/testtoeic/listening/ets2020/test/new')
                        },
                        edit: {
                            root: path(ROOTS_DASHBOARD, '/onlinetest/testtoeic/listening/ets2020/test/edit')
                        },
                        detail: {
                            root: path(ROOTS_DASHBOARD, '/onlinetest/testtoeic/listening/ets2020/test/details')
                        }
                    }
                },
                ets2021: {
                    lesson: {
                        root: path(ROOTS_DASHBOARD, '/onlinetest/testtoeic/listening/ets2021/lesson')
                    },
                    test: {
                        root: path(ROOTS_DASHBOARD, '/onlinetest/testtoeic/listening/ets2021/test'),
                        add: {
                            root: path(ROOTS_DASHBOARD, '/onlinetest/testtoeic/listening/ets2021/test/new')
                        },
                        edit: {
                            root: path(ROOTS_DASHBOARD, '/onlinetest/testtoeic/listening/ets2021/test/edit')
                        },
                        detail: {
                            root: path(ROOTS_DASHBOARD, '/onlinetest/testtoeic/listening/ets2021/test/details')
                        }
                    }
                },
                ets2022: {
                    lesson: {
                        root: path(ROOTS_DASHBOARD, '/onlinetest/testtoeic/listening/ets2022/lesson')
                    },
                    test: {
                        root: path(ROOTS_DASHBOARD, '/onlinetest/testtoeic/listening/ets2022/test'),
                        add: {
                            root: path(ROOTS_DASHBOARD, '/onlinetest/testtoeic/listening/ets2022/test/new')
                        },
                        edit: {
                            root: path(ROOTS_DASHBOARD, '/onlinetest/testtoeic/listening/ets2022/test/edit')
                        },
                        detail: {
                            root: path(ROOTS_DASHBOARD, '/onlinetest/testtoeic/listening/ets2022/test/details')
                        }
                    }
                },
                ets2023: {
                    lesson: {
                        root: path(ROOTS_DASHBOARD, '/onlinetest/testtoeic/listening/ets2023/lesson')
                    },
                    test: {
                        root: path(ROOTS_DASHBOARD, '/onlinetest/testtoeic/listening/ets2023/test'),
                        add: {
                            root: path(ROOTS_DASHBOARD, '/onlinetest/testtoeic/listening/ets2023/test/new')
                        },
                        edit: {
                            root: path(ROOTS_DASHBOARD, '/onlinetest/testtoeic/listening/ets2023/test/edit')
                        },
                        detail: {
                            root: path(ROOTS_DASHBOARD, '/onlinetest/testtoeic/listening/ets2023/test/details')
                        }
                    }
                }
            }
        }
    },

    grammar: {
        wordAndGrammar: {
            newWord: {
                root: path(ROOTS_DASHBOARD, '/grammar/wordgrammar/newword'),
                new: path(ROOTS_DASHBOARD, '/grammar/wordgrammar/newword/new'),
                detail: {
                    root: path(ROOTS_DASHBOARD, '/grammar/wordgrammar/newword/detail'),
                    editWord: path(ROOTS_DASHBOARD, '/grammar/wordgrammar/newword/detail/editword'),
                }
            },
            grammar: {
                root: path(ROOTS_DASHBOARD, '/grammar/wordgrammar/grammar'),
                new: path(ROOTS_DASHBOARD, '/grammar/wordgrammar/grammar/new'),
                edit: path(ROOTS_DASHBOARD, '/grammar/wordgrammar/grammar/edit'),
            }
        },
        topics: {
            root: path(ROOTS_DASHBOARD, '/grammar/topics'),
            new: path(ROOTS_DASHBOARD, '/grammar/topics/new'),
            edit: path(ROOTS_DASHBOARD, '/grammar/topics/new'),
        },
        exercise: {
            root: path(ROOTS_DASHBOARD, '/grammar/exercise'),
            new: path(ROOTS_DASHBOARD, '/grammar/exercise/new'),
            edit: path(ROOTS_DASHBOARD, '/grammar/exercise/new'),
        }

    },

    flashcards: {
        root: path(ROOTS_DASHBOARD, '/flashcard/wordList'),
        detail: path(ROOTS_DASHBOARD, '/flashcard/wordList/detail'),
        word: path(ROOTS_DASHBOARD, '/flashcard/wordList/detail/word')
    },

    general: {
        app: path(ROOTS_DASHBOARD, '/app'),
    },

    user: {
        root: path(ROOTS_DASHBOARD, '/system/user'),
        new: path(ROOTS_DASHBOARD, '/system/user/new'),
        list: path(ROOTS_DASHBOARD, '/system/user/list'),
        account: path(ROOTS_DASHBOARD, '/system/user/account'),
        edit: (name) => path(ROOTS_DASHBOARD, `/system/user/${name}/edit`),
    },

    class: {
        root: path(ROOTS_DASHBOARD, '/system/class'),
        new: path(ROOTS_DASHBOARD, '/system/class/new'),
        edit: path(ROOTS_DASHBOARD, '/system/class/edit'),
        detail: path(ROOTS_DASHBOARD, '/system/class/userClass'),
        addUser: path(ROOTS_DASHBOARD, '/system/class/addStudent')
    },


    role: {
        root: path(ROOTS_DASHBOARD, '/system/roles'),
        new: path(ROOTS_DASHBOARD, '/system/roles/new'),
        list: path(ROOTS_DASHBOARD, '/system/roles/list'),
        edit: (name) => path(ROOTS_DASHBOARD, `/system/roles/${name}/edit`),
    },
    permission: {
        root: path(ROOTS_DASHBOARD, '/system/permission'),
        new: path(ROOTS_DASHBOARD, '/system/permission/new'),
        list: path(ROOTS_DASHBOARD, '/system/permission/list'),
        edit: (name) => path(ROOTS_DASHBOARD, `/system/permission/${name}/edit`),
    },
    menu: {
        root: path(ROOTS_DASHBOARD, '/system/menu'),
        new: path(ROOTS_DASHBOARD, '/system/menu/new'),
        list: path(ROOTS_DASHBOARD, '/system/menu/list'),
        edit: (id) => path(ROOTS_DASHBOARD, `/system/menu/${id}/edit`),
    },
    uploadfiles: {
        root: path(ROOTS_DASHBOARD, `/system/uploadfiles`),
        link: (uploadfile_id) => path(ROOTS_DASHBOARD, `/system/uploadfiles/${uploadfile_id ? uploadfile_id : 0}`),
        newDocument: (uploadfile_id) => path(ROOTS_DASHBOARD, `/system/uploadfiles/${uploadfile_id ? uploadfile_id : 0}/new`),
    },

    ielttest: {
        root: path(ROOTS_DASHBOARD, '/onlinetest/ielts'),
    }
};
