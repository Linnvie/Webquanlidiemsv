import {configureStore} from '@reduxjs/toolkit'
import useReducer from './useSlices'
import {persistStore} from 'redux-persist'

const store =configureStore({
    reducer: {
        user: useReducer
    }
})

const persistor = persistStore(store)

export {store, persistor}