export default function authHeader() {
    const token = JSON.parse(localStorage.getItem('accessToken'));
   if (token) {
    
       return { Authorization: 'Bearer ' +token }; // for Spring Boot back-end
      //return { 'x-access-token': user.accessToken };       // for Node.js Express back-end
    } else {
      return {};
    }
  }