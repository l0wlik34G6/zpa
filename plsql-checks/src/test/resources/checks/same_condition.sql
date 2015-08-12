begin
  -- violations
  var := (x = 1 and x = 1);
  var := (x = 1 or x = 1);
  
  select tab.col
    into var
    from tab
   where tab.col = 1
     and tab.col = 1;
     
  select tab.col
    into var
    from tab
   where (tab.col = 1 and tab.col2 = 2)
      or (tab.col = 1 and tab.col2 = 2);
  
  -- correct
  var := (x = 1 and y = 2);
  var := (x = 1 or y = 2);
  
  -- no violation because it is equivalent to ((x = 1 or y = 2) and x = 1)
  -- but we could report a violation someday
  var := (x = 1 or y = 2 and x = 1);
  
  -- we could report a violation someday in this case too
  var := (x = 1 and 1 = x);
end;