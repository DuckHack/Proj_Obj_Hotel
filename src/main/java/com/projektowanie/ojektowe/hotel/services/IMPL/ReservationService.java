package com.projektowanie.ojektowe.hotel.services.IMPL;

import com.projektowanie.ojektowe.hotel.exceptions.ReservationEndBeforeStartException;
import com.projektowanie.ojektowe.hotel.models.Reservation;
import com.projektowanie.ojektowe.hotel.models.utils.DISCOUNT_PERIODS;
import com.projektowanie.ojektowe.hotel.models.utils.ReservationFilter;
import com.projektowanie.ojektowe.hotel.repositories.ReservationRepository;
import com.projektowanie.ojektowe.hotel.services.IReservationService;

import javax.validation.constraints.NotBlank;
import java.util.*;


public class ReservationService implements IReservationService {

    private final Integer reservationsNumForStartDiscount = 5;
    private final Integer reservationsNumForHoldDiscountSize = 30;
    private ReservationRepository reservationRepository;

    public ReservationService(ReservationRepository reservationRepository){
        this.reservationRepository = reservationRepository;
    }

    @Override
    public Reservation add(Reservation reservation) throws ReservationEndBeforeStartException{
        if( reservation.getEnd().before(reservation.getStart())){
            throw new ReservationEndBeforeStartException("Reservation end date can't be before start");
        }
        reservation.setPrice( updateReservationBySeasonDiscount(reservation) );
        reservation.setPrice( updateReservationByConstUserDiscount(reservation) );
        return reservationRepository.save(reservation);
    }

    @Override
    public List<Reservation> getById(ReservationFilter reservationFilter){
//        if( reservationFilter.getOwnerId() != null ){
//            return reservationRepository.findByOwnerId(reservationFilter.getOwnerId());
//        }else if( reservationFilter.getRoom() != null){
//            return reservationRepository.findAllByRoomNumber(reservationFilter.getRoom());
//        }else {
//            throw new EmptyReservationFilterException("Empty reservation filter got");
//        }

//        return reservationRepository.findByOwnerId(reservationFilter.getOwnerId())
//                .stream().filter(reservation -> (reservation.getRoom().equals(reservationFilter.getRoom())))
//                .collect(Collectors.toList());
        return reservationRepository.findByOwnerId(reservationFilter.getOwnerId());
    }

    @Override
    public void delete(@NotBlank String id){
        reservationRepository.deleteById(Integer.parseInt(id));
    }

    public Double updateReservationBySeasonDiscount(Reservation reservation){
        for(DISCOUNT_PERIODS discountPeriod: DISCOUNT_PERIODS.values()){
            if( isInPeriod(reservation, discountPeriod)){
                reservation.setPrice( reservation.getPrice() - reservation.getPrice()*discountPeriod.getDiscountSize()/100);
            }
        }
        return reservation.getPrice();
    }

    private Boolean isInPeriod(Reservation reservation, DISCOUNT_PERIODS discount_period){
        Date discountStart = new Date(reservation.getStart().getYear(), discount_period.getStartMonth(), discount_period.getStartDay());
        Date discountEnd = new Date(reservation.getEnd().getYear(), discount_period.getEndMonth(), discount_period.getEndDay());
        return discountStart.getTime() == reservation.getStart().getTime() || discountStart.before(reservation.getStart()) &&
                discountEnd.getTime() == reservation.getEnd().getTime() || discountEnd.before(reservation.getEnd());
    }

    public Double updateReservationByConstUserDiscount(Reservation reservation){
        int reservationsNum = reservationRepository.findByOwnerId(reservation.getOwnerId()).size();

        if( reservationsNum >= reservationsNumForStartDiscount ){
            reservation.setPrice( reservation.getPrice() - reservation.getPrice()*reservationsNum/100);
        }else if( reservationsNum >= reservationsNumForHoldDiscountSize ){
            reservation.setPrice( reservation.getPrice() - reservation.getPrice()*reservationsNumForHoldDiscountSize/100);
        }
        return reservation.getPrice();
    }
}
