import http from "../http-common";


    
    const getAll = () => {
        return http.get("/projet/specialites");
    }

    const SpecialiteService = {
        getAll
    }
    

export default SpecialiteService;