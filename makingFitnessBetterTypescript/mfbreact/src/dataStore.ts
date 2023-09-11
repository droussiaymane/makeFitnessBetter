import { createContext } from 'react';

interface AuthContext {
    token: any;
    setToken: any;
}

export const AuthContext = createContext<AuthContext>(<AuthContext>{
    token:null,
    setToken:null,
});