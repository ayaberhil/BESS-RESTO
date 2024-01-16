import React from "react";
import { Link } from "react-router-dom";
import { GrRestaurant,GrHomeRounded, GrLocation} from "react-icons/gr"

const Navbar = () => {
  return (
    <nav>
      <div className="fs-1 fw-bold">
        <Link to={"/projet/"} className="d-flex justify-content-center align-items-center m-1 navbar-brand">
          < GrLocation className="m-2" />
          BESS RESTO
        </Link>
      </div>
      <div class="row justify-content-center ">
        <div class="col-4  nav-btn shadow-sm">
          <Link to={"/projet/"} className="m-1 text-center nav-link" style={{color:"white"}}>
            <GrHomeRounded className="m-2"/>
            Acceuil
          </Link>
        </div>
        <div class="col-4  nav-btn shadow-sm">
          <Link to={"/projet/list"} className="m-1 text-center nav-link" style={{color:"white"}}>
            <GrRestaurant className="m-2"/>
            Restaurants
          </Link>
        </div>
      </div>
    </nav>
  );
};
export default Navbar;
