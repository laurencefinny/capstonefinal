import * as actionTypes from './Action';
const initialState={
   userData:''

}

const Reducer = (state = initialState, action) => {
    console.log("actionval:",action.value);
    switch(action.type) {

        case actionTypes.LOGIN:
   
            return {
               
            ...state,
            userData: action.value
        }
         
    }
    
    return state;
}
export default Reducer;
