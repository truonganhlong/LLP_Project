import _mock from '../_mock';
import { randomNumberRange, randomInArray } from '../utils';
import { any } from "prop-types";

// ----------------------------------------------------------------------


export const _customerList = [...Array(24)].map((_, index) => ({
    id: _mock.id(index),
    addressName: String,
    zipCode: String,
    orderCount: String,
    nowOrderCount: String,
    note: String,
    createTime: String,
}));


