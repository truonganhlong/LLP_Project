// routes
import { PATH_DASHBOARD, PATH_PAGES } from './routes/paths';

// API
// ----------------------------------------------------------------------

export const HOST_API_KEY = process.env.HOST_API_KEY || '';
export const PAGE_SIZE = 10;
export const ROLES_CODE = {
    SHIPPER: 'SHIPPER',
    SELLER: 'SELLER',
    ADMIN: 'ADMIN',
    GVCHUNHIEM: 'GVCHUNHIEM',
    TRUONGBOMON: 'TRUONGBOMON',
};

export const COGNITO_API = {
    userPoolId: process.env.AWS_COGNITO_USER_POOL_ID,
    clientId: process.env.AWS_COGNITO_CLIENT_ID,
};

export const AUTH0_API = {
    clientId: process.env.AUTH0_CLIENT_ID,
    domain: process.env.AUTH0_DOMAIN,
};

export const MAPBOX_API = process.env.MAPBOX_API;

// ROOT PATH AFTER LOGIN SUCCESSFUL
export const PATH_AFTER_LOGIN = PATH_PAGES.home; // as '/dashboard/app'

// LAYOUT
// ----------------------------------------------------------------------

export const HEADER = {
    H_MOBILE: 80 - 32, // old 64
    H_MAIN_DESKTOP: 80 - 32, // old 70
    H_DASHBOARD_DESKTOP: 80 - 32,  //old 80
    H_DASHBOARD_DESKTOP_OFFSET: 80 - 32,
};

export const URL_GLOBAL = {
    DOWNLOAD_FILE: 'https://localhost:7287/api/File/downloadFile?',
    VIEW_FILE: 'http://localhost:3000/uploads/',
};

export const NAV = {
    W_BASE: 260,
    W_DASHBOARD: 280,
    W_DASHBOARD_MINI: 88,
    //
    H_DASHBOARD_ITEM: 48,
    H_DASHBOARD_ITEM_SUB: 36,
    //
    H_DASHBOARD_ITEM_HORIZONTAL: 28,
};

export const ICON = {
    NAV_ITEM: 24,
    NAV_ITEM_HORIZONTAL: 22,
    NAV_ITEM_MINI: 22,
};
