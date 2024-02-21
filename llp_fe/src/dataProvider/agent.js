import axios from 'axios';
//http://103.161.178.66:8003/api/ https://localhost:7287/api,http://103.161.178.66:8004/api/
const instance = axios.create({
    baseURL: `http://103.161.178.66:8003/api/`,
    timeout: 60000,
});

const instanceAdmin = axios.create({
    baseURL: `http://103.161.178.66:8004/api`,
    timeout: 60000,
});

instance.interceptors.response.use(responseOnSuccessMiddleware, responseOnErrorMiddleware);

function responseOnSuccessMiddleware(res) {
    return res;
}

function responseOnErrorMiddleware(error) {
    const { status } = error.response;
    if (status === 401) {
        localStorage.clear();
        window.location.href = PATH_AUTH.login;
    }
    return error;
}

// INTERCEPTORS CONFIG END

const setLocalStorage = (key, value) => {
    localStorage.setItem(key, JSON.stringify(value));
};
const getLocalStorage = (key) => {
    if (typeof window !== 'undefined') {
        return JSON.parse(localStorage.getItem(key));
    }
};

const clearLocalStorage = () => {
    localStorage.clear();
};



async function getApi(url, params) {
    // delete all params fail
    const paramObj = {};
    if (params && Object.keys(params).length) {
        Object.keys(params).forEach(function (key) {
            if (params[key]) {
                paramObj[key] = params[key];
            }
        });
    }

    const token = getLocalStorage('access_token');
    try {
        const res = await instance.get(url, {
            headers: {
                Authorization: token ? `Bearer ${token}` : 'no auth',
            },
            params: paramObj,
        });
        return res;
    } catch (err) {
        return err;
    }
}

async function getApiAdmin(url, params) {
    // delete all params fail
    const paramObj = {};
    if (params && Object.keys(params).length) {
        Object.keys(params).forEach(function (key) {
            if (params[key]) {
                paramObj[key] = params[key];
            }
        });
    }

    const token = getLocalStorage('access_token_admin');
    try {
        const res = await instanceAdmin.get(url, {
            headers: {
                Authorization: token ? `Bearer ${token}` : 'no auth',
            },
            params: paramObj,
        });
        return res;
    } catch (err) {
        return err;
    }
}


function getAllCategory() {
    return getApi(`/TestIndex/getTestCourse`)
}

export {
    getAllCategory
}




