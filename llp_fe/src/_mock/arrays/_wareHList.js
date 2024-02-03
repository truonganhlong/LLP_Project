import _mock from '../_mock';
import { randomNumberRange, randomInArray } from '../utils';
import { any } from "prop-types";

// ----------------------------------------------------------------------


export const _wareHList = [...Array(24)].map((_, index) => ({
    id: _mock.id(index),
    item: String,
    itemOption: String,
    itemSold: String,
    retailPrice: Float32Array,
    salePrice: Float32Array,
    upPrice: Float32Array,
    inStock: String,
    ebayAccount: String,
    note: String,
    createTime: Date,
}));


