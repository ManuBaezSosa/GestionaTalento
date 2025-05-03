package com.gestionatalento.gestiona_talento.Mapper;


import org.mapstruct.Mapper;

import com.gestionatalento.gestiona_talento.Dto.EventoDto;
import com.gestionatalento.gestiona_talento.Entity.Evento;

@Mapper
public interface EventoMapper {

    public static Evento setEvento(EventoDto eventoDto){
        Evento evento = new Evento();
        evento.setDescripcion(eventoDto.getDescripcion());
        evento.setFecha(eventoDto.getFecha());
        evento.setHoraFinal(eventoDto.getHoraFinal());
        evento.setHoraInicial(eventoDto.getHoraFinal());
        evento.setTipoEvento(eventoDto.getTipoEvento());
        evento.setVigente("S");

        return evento;
    }
    public static Evento setActualizarEvento(EventoDto eventoDto) {
        Evento evento = new Evento();
        evento.setNroEvento(eventoDto.getNroEvento());
        evento.setDescripcion(eventoDto.getDescripcion());
        evento.setFecha(eventoDto.getFecha());
        evento.setHoraFinal(eventoDto.getHoraFinal());
        evento.setHoraInicial(eventoDto.getHoraFinal());
        evento.setTipoEvento(eventoDto.getTipoEvento());
        evento.setVigente(eventoDto.getVigente());

        return evento;
    }
    
}
