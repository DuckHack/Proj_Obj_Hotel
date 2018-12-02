package com.projektowanie.ojektowe.hotel.services.Factories;

import com.projektowanie.ojektowe.hotel.repositories.ReservationRepository;
import com.projektowanie.ojektowe.hotel.services.IMPL.ReservationService;
import com.projektowanie.ojektowe.hotel.services.IReservationService;

public class ReservationServiceFactory {
    public static IReservationService getReservationService(ReservationRepository reservationRepository) {
        return new ReservationService(reservationRepository);
    }
}
