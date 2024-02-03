import { useRouter } from 'next/router';

// ----------------------------------------------------------------------

export default function useActiveLink(path, deep = true) {
  const { pathname, asPath } = useRouter();

  const checkPath = path.startsWith('#');

  const currentPath = path === '/' ? '/' : `${path}`;

  const normalActive = (!checkPath && pathname === currentPath) || (!checkPath && asPath === currentPath);

  const deepActive = (!checkPath && pathname.includes(currentPath.split("?")?.[0])) || (!checkPath && asPath.includes(currentPath.split("?")?.[0]));

  return {
    active: deep ? deepActive : normalActive,
    isExternalLink: path.includes('http'),
  };
}
