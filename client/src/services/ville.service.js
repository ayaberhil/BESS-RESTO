import http from "../http-common";

    const getAll = () => {
        return http.get("/projet/villes");
    }

    const VilleService = {
        getAll
    }


    

export default VilleService;