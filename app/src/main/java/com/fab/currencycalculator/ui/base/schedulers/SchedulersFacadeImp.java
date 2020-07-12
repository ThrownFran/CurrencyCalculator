package com.fab.currencycalculator.ui.base.schedulers;

import com.fab.currencycalculator.domain.schedulers.SchedulersFacade;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class SchedulersFacadeImp implements SchedulersFacade {

    @Override
    public Scheduler io () {
        return Schedulers.io();
    }

    @Override
    public Scheduler computation () {
        return Schedulers.computation();
    }

    @Override
    public Scheduler ui () {
        return AndroidSchedulers.mainThread();
    }
}
