import _mock from '../_mock';
import { randomNumberRange, randomInArray } from '../utils';
import { any } from "prop-types";

// ----------------------------------------------------------------------


export const _orderList = [...Array(24)].map((_, index) => ({
    id: _mock.id(index),
    itemData: String,
    itemOption: String,
    netRevenue: String,
    customer: String,
    orderDate: Date,
    ebAccount: String,
    status: String,
    orderInfo: String,
    tracking: String,
    note: String,
    shipper: String,
    seller: String,
}));


