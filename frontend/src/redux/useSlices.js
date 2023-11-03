import { createSlice } from "@reduxjs/toolkit";

const useSlice = createSlice({
    name: "user",
    initialState: {
        user: null
    },
    reducers: {
        login: (state, action) => {
            state.user = action.payload
        },
        logout: (state) => {
            state.user = null
        }
    }
})

export const {login, logout} = useSlice.actions
export const selectUser = (state) => state.user.user

export {useSlice}
export default useSlice.reducer