import React from "react";


function Photo(props) {
    return (
        <div key={props.key} className="carousel-item active">
            <img src={props.src} className="d-block w-100"></img>
        </div>
    );
}
export default Photo;