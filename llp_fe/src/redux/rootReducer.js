import { combineReducers } from 'redux';
import { persistReducer } from 'redux-persist';
import createWebStorage from 'redux-persist/lib/storage/createWebStorage';
// slices
import userReducer from './slices/user';
import dataReducer from './slices/data';
import passageReducer from './slices/passage';

// ----------------------------------------------------------------------

const createNoopStorage = () => ({
    getItem() {
        return Promise.resolve(null);
    },
    setItem(_key, value) {
        return Promise.resolve(value);
    },
    removeItem() {
        return Promise.resolve();
    },
});

const storage = typeof window !== 'undefined' ? createWebStorage('local') : createNoopStorage();

const rootPersistConfig = {
    key: 'root',
    storage,
    keyPrefix: 'redux-',
    whitelist: [],
};

const productPersistConfig = {
    key: 'product',
    storage,
    keyPrefix: 'redux-',
    whitelist: ['sortBy', 'checkout'],
};

const rootReducer = combineReducers({
    user: userReducer,
    data: dataReducer,
    passage: passageReducer,
});

export { rootPersistConfig, rootReducer };
