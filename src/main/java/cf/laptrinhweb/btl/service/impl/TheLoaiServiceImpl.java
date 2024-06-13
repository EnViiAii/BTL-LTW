package cf.laptrinhweb.btl.service.impl;

import cf.laptrinhweb.btl.entity.TheLoai;
import cf.laptrinhweb.btl.repository.TheLoaiRepository;
import cf.laptrinhweb.btl.repository.impl.TheLoaiRepositoryImpl;
import cf.laptrinhweb.btl.service.TheLoaiService;

import java.util.ArrayList;
import java.util.List;

public class TheLoaiServiceImpl implements TheLoaiService {
    private final TheLoaiRepository theLoaiRepository = new TheLoaiRepositoryImpl();

    @Override
    public List<TheLoai> layTatCa() {
        return theLoaiRepository.layTatCa();
    }
}
