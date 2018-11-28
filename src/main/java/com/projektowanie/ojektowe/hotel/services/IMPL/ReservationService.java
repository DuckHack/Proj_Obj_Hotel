package com.projektowanie.ojektowe.hotel.services.IMPL;

import com.projektowanie.ojektowe.hotel.exceptions.EmptyReservationFilterException;
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
//        for every celebration
//        is celebraion
//        use decorator for this celebration
        if( reservation.getEnd().before(reservation.getStart())){
            throw new ReservationEndBeforeStartException("Reservation end date can't be before start");
        }
        updateReservationByDiscount(reservation);
        return reservationRepository.save( reservation );
    }

    @Override
    public List<Reservation> getByNameOrId(ReservationFilter reservationFilter)throws EmptyReservationFilterException{
        if( reservationFilter.getOwnerId() != null ){
            return reservationRepository.findByOwnerId(reservationFilter.getOwnerId());
        }else if( reservationFilter.getReservePerson() != null){
            return reservationRepository.findByReservePerson(reservationFilter.getReservePerson());
        }else if( reservationFilter.getRoom() != null){
            return reservationRepository.findAllByRoomNumber(reservationFilter.getRoom());
        }else {
            throw new EmptyReservationFilterException("Empty reservation filter got");
        }
    }

    @Override
    public void delete(@NotBlank String id){
        reservationRepository.deleteById(Integer.parseInt(id));
    }

    private void updateReservationByDiscount(Reservation reservation){
        int reservationsNum = reservationRepository.findByReservePerson(reservation.getOwner()).size();
        for(DISCOUNT_PERIODS discountPeriod: DISCOUNT_PERIODS.values()){
            if( isInPeriod(reservation, discountPeriod)){
                reservation.setPrice( reservation.getPrice() - reservation.getPrice()*discountPeriod.getDiscountSize()/100);
            }
        }

        if( reservationsNum >= reservationsNumForStartDiscount ){
            reservation.setPrice( reservation.getPrice() - reservation.getPrice()*reservationsNum/100);
        }else if( reservationsNum >= reservationsNumForHoldDiscountSize ){
            reservation.setPrice( reservation.getPrice() - reservation.getPrice()*reservationsNumForHoldDiscountSize/100);
        }
    }

    private Boolean isInPeriod(Reservation reservation, DISCOUNT_PERIODS discount_period){
        Date discountStart = new Date(reservation.getStart().getYear(), discount_period.getStartMonth(), discount_period.getStartDay());
        Date discountEnd = new Date(reservation.getEnd().getYear(), discount_period.getEndMonth(), discount_period.getEndDay());
        return discountStart.getTime() == reservation.getStart().getTime() || discountStart.before(reservation.getStart()) &&
                discountEnd.getTime() == reservation.getEnd().getTime() || discountEnd.before(reservation.getEnd());
    }
}
