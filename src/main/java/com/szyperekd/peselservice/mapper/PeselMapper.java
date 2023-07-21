package com.szyperekd.peselservice.mapper;

import com.szyperekd.peselservice.dto.PeselRequest;
import com.szyperekd.peselservice.dto.PeselResponse;
import com.szyperekd.peselservice.service.Pesel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PeselMapper {


    Pesel mapPeselRequestToPesel(PeselRequest peselRequest);

    PeselResponse mapPeselToPeselResponse(Pesel decodedPesel);
}
