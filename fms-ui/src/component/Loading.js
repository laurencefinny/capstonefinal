import React from "react";

const Loading = React.memo( props => {


    return (
        <React.Fragment>
            <div
                className="spinner-border text-secondary" 
                style={{display:'block',position:'fixed',zIndex:1031,top:'50%',right:'50%'}} 
                role="status">
                <span className="sr-only">Loading...</span>
            </div>
        </React.Fragment>
    )

});

export default Loading;