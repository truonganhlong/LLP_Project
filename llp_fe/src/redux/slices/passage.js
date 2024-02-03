import { createSlice } from '@reduxjs/toolkit';
import { dispatch } from '../store';
import { getAllPassage } from '../../dataProvider/agent';

const initialState = {
    isLoading: false,
    error: null,
    passages: [],
};

const passageSlice = createSlice({
    name: 'passage',
    initialState,
    reducers: {
        startLoading(state) {
            state.isLoading = true;
        },

        hasError(state, action) {
            state.isLoading = false;
            state.error = action.payload;
        },

        getPassageSuccess(state, action) {
            console.log("Get All: ", action);
            const { respone } = action.payload;
            state.isLoading = false;
            return {
                passages: respone.data.data
            }
        },

        setPassages(state, action) {
            state.isLoading = false;
            state.passages = action.payload;
        },
    },
});

export default passageSlice.reducer;

export const { actions } = passageSlice;

const returnMessageSuccess = (title) => ({
    title: `${title}`,
    variant: 'success',
});

const returnMessageError = (title) => ({
    title: `${title}`,
    variant: 'error',
});

export function getPassageRedux(param, index) {
    return async () => {
        try {
            if (!param) {
                return dispatch(passageSlice.actions.getPassageSuccess([]))
            }
            dispatch(passageSlice.actions.startLoading())
            const response = await getPassAll()
            dispatch(
                passageSlice.actions.getPassageSuccess({
                    passages: response,
                }
                ))
        } catch (error) {
            dispatch(passageSlice.actions.hasError(error))
        }
    }
}