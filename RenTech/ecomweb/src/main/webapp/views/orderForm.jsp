<div class="modal-dialog tutors-modal">
    <div class="modal-content tutors-modal">
        <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
            <h4 class="modal-title">Appointment Scheduler</h4>
        </div>
        <div class="modal-body" style="height: 575px; left: 2.5%;">
            <div class="container">
                <div class="row">
                    <div class="col-md-12">     
                        <div class="alert alert-danger"><span class="glyphicon glyphicon-alert"></span>
                            <form role="form" action="/portal/make-appointment/" method="post" id="appointment_form" class="form-horizontal">
                                <div class="col-lg-6">

                                    <div class="input-group clockpicker" id = "clockpicker" style="padding: 6px; padding-bottom: 20px;">
                                        <input type="text" class="form-control" id = "time" name="time" value="08:00">
                                        <span class="input-group-addon">
                                            <span class="glyphicon glyphicon-time"></span>
                                        </span>
                                    </div>
                                    <script type="text/javascript">
                                        var input = $('#time');
                                        input.clockpicker({
                                            autoclose: true
                                        });
                                    </script>
                                    <input type="submit" name="submit" id="submit" value="Schedule Appointment" class="btn btn-info pull-right">
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="modal-footer">
        </div>
    </div>
</div>