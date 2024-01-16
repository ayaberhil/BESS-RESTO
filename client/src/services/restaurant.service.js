import http from "../http-common";

    const getAll = () => {
        return http.get(`/projet/all`);
    }

    const getAllByRestaurant = id => {
        return http.get(`/projet/photos/${id}`);
    }

    const getAllBySerie = id => {
        return http.get(`/projet/serie/${id}`);
    }

    const getAllBySpecialite = id => {
        return http.get(`/projet/specialite/${id}`);
    }

    const getAllByVille = id => {
        return http.get(`/projet/ville/${id}`);
    }

    const getAllByZone = id => {
        return http.get(`/projet/zone/${id}`);
    }

    const getAllByVilleAndZone = (villeId, zoneId) => {
        return http.get(`/projet/${villeId}/${zoneId}`);
    }

    const RestaurantService = {
        getAll,
        getAllByRestaurant,
        getAllBySpecialite,
        getAllByVille,
        getAllByZone,
        getAllBySerie,
        getAllByVilleAndZone
    }

    
    

export default RestaurantService;