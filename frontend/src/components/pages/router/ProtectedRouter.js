import { Navigate, NavLink, Outlet } from 'react-router-dom';
import Login from '../Login/Login';

function ProtectedRouter({ user, redirectPath = '/', children }) {
    // const isAuthenicated = false
    // return isAuthenicated ? <Outlet/> : <Login/>
    if (!user) {
        return <Navigate to={redirectPath} replace />;
      }
    
      return children ? children : <Outlet />
}

export default ProtectedRouter;