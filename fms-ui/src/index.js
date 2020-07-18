import React from 'react';
import ReactDOM from 'react-dom';
import './index.css';
import App from './App';
import '@fortawesome/fontawesome-free/css/all.min.css'; 
import 'bootstrap-css-only/css/bootstrap.min.css'; 
import 'mdbreact/dist/css/mdb.css';
import Reducer from './redux/Reducer';
import {createStore,applyMiddleware} from 'redux';
import {Provider} from 'react-redux';
import Favicon from 'react-favicon';
const  logger = store =>{
    return next =>{
        return action =>{
            console.log('[Middleware] dispatching',action);
            console.log('[Middleware] before state',store.getState())
            const result = next(action);
            console.log('[Middleware] after state',store.getState());
            return result;
        }
    }
}

const store = createStore(Reducer,applyMiddleware(logger));
ReactDOM.render(<div>
    <Favicon url="https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcSwiyHkGeFtJ2lKTiY8Wr7M6zo8AjLIHUYPT-tqNXrJqY_kj04J" />
    <Provider store={store}> <App /></Provider>
  </div>, document.getElementById('root'));


